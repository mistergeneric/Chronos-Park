package model.tree;

import model.Preference;
import model.Ride;
import util.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    private TreeNode root;

    public Tree(Ride ride) {
        this.root = new TreeNode("What is the number of people in party?", null, "group", true);

        TreeNode smallestHeight = new TreeNode("What is the height of the smallest person?", root, "smallHeight", true);
        TreeNode largestHeight = new TreeNode("What is the height of the tallest person?", root, "tallHeight", true);
        root.setLeft(smallestHeight);
        root.setRight(largestHeight);

        TreeNode wheelchair = new TreeNode("Does anyone in the group use a wheelchair?", smallestHeight, "preference", ride.isWheelchair());
        TreeNode kids = new TreeNode("Does anyone have preference for kids rides?", smallestHeight, "preference", ride.isKidFriendly());
        smallestHeight.setLeft(wheelchair);
        smallestHeight.setRight(kids);

        TreeNode horror = new TreeNode("Does anyone have preference for horror rides?", largestHeight, "preference", ride.isHorror());
        TreeNode water = new TreeNode("Does anyone have preference for water rides?", largestHeight, "preference", ride.isWater());
        largestHeight.setLeft(horror);
        largestHeight.setRight(water);

        TreeNode adrenaline = new TreeNode("Does anymore have preference for adrenaline?", horror, "preference", ride.isAdrenaline());
        wheelchair.setLeft(adrenaline);
    }

    public boolean checkGroupSize(float smallestGroupSizeOnRide, float biggestGroupSizeOnRide, float groupSize) {
        if (groupSize <= biggestGroupSizeOnRide && groupSize >= smallestGroupSizeOnRide) {
            return true;
        }
        return false;
    }

    public boolean checkHeight(float minimumAllowedHeight, float maximumAllowedHeight, float shortestUserHeight, float tallestUserHeight) {
        if (minimumAllowedHeight > shortestUserHeight) {
            return false;
        }
        if (maximumAllowedHeight < tallestUserHeight) {
            return false;
        }
        return true;
    }

    public Preference question(Ride ride) {
        //how to traverse the tree of questions?
        //keep a string of the preferences and print out
        TreeNode node = this.root;
        boolean isRideRecommended = false;
        float shortestHeight = 0;
        float tallestHeight = 0;
        int groupSize = 0;
        String answer;
        String preferences = "";

        //we are going to use bfs to traverse
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode question = queue.poll();


            System.out.println(question.getQuestion());
            if (question.getType().equals("smallHeight")) {
                shortestHeight = heightValidator(shortestHeight);
            } else if (question.getType().equals("tallHeight")) {
                tallestHeight = heightValidator(tallestHeight);
                tallestHeight = tallestHeightIsValid(shortestHeight, tallestHeight);
            } else if (question.getType().equals("group")) {
                groupSize = Utils.getNumber();
            } else {
                answer = Utils.yesOrNo();
                preferences += "The group answered to the question " + question.getQuestion() + "\n" + (answer.equals("Y") ? "Yes" : "No") + "\n ----------- \n";
                if (answer.equals("Y")) {
                    if (question.isRecommended()) {
                        isRideRecommended = true;
                    }
                }
            }
            if (question.getLeft() != null) {
                queue.add(question.getLeft());
            }
            if (question.getRight() != null) {
                queue.add(question.getRight());
            }

        }
        isRideRecommended = answerValidator(ride, isRideRecommended, shortestHeight, tallestHeight, groupSize);
        return new Preference(isRideRecommended, preferences);
    }

    public float tallestHeightIsValid(float shortestHeight, float tallestHeight) {
        while (true) {
            if (tallestHeight < shortestHeight) {
                System.out.println("Tallest height cannot be smaller than shortest, please try again");
                tallestHeight = 0;
                tallestHeight = heightValidator(tallestHeight);
            } else {
                break;
            }
        }
        return tallestHeight;
    }


    public float heightValidator(float height) {
        while (height <= 0 || height >= 3) {
            height = Utils.getFloat();
            if (height >= 3 || height <= 0) {
                System.out.println("Are you sure about that? Try again");
            }
        }
        return height;
    }

    public boolean answerValidator(Ride ride, boolean isRideRecommended, float shortestHeight, float tallestHeight, int groupSize) {
        boolean isValidHeight = checkHeight(ride.getMinimumHeight(), ride.getMaximumHeight(), shortestHeight, tallestHeight);
        boolean isValidGroupSize = checkGroupSize(ride.getSmallestNumberCanRide(), ride.getBiggestNumberCanRide(), groupSize);
        if (!isValidHeight || !isValidGroupSize) {
            isRideRecommended = false;
        }
        return isRideRecommended;
    }
}