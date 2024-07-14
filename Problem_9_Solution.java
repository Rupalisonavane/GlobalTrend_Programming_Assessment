import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
class IntervalNode {
    Interval interval;
    int maxEnd;
    IntervalNode left;
    IntervalNode right;

    public IntervalNode(Interval interval) {
        this.interval = interval;
        this.maxEnd = interval.end;
        this.left = null;
        this.right = null;
    }
}


class IntervalTree {
    private IntervalNode root;

    public IntervalTree() {
        this.root = null;
    }

    public void insertInterval(int start, int end) {
        Interval newInterval = new Interval(start, end);
        this.root = insertHelper(root, newInterval);
    }

    private IntervalNode insertHelper(IntervalNode node, Interval newInterval) {
        if (node == null) {
            return new IntervalNode(newInterval);
        }

        node.maxEnd = Math.max(node.maxEnd, newInterval.end);

        if (newInterval.start < node.interval.start) {
            node.left = insertHelper(node.left, newInterval);
        } else { 
            node.right = insertHelper(node.right, newInterval);
        }

        return node;
    }

    public void deleteInterval(int start, int end) {
        this.root = deleteHelper(root, new Interval(start, end));
    }

    private IntervalNode deleteHelper(IntervalNode node, Interval intervalToDelete) {
        if (node == null) {
            return null;
        }

        if (intervalToDelete.start < node.interval.start) {
            node.left = deleteHelper(node.left, intervalToDelete);
        } else if (intervalToDelete.start > node.interval.start) {
            node.right = deleteHelper(node.right, intervalToDelete);
        } else {
            if (intervalToDelete.end != node.interval.end) {
                node.right = deleteHelper(node.right, intervalToDelete);
            } else {
                if (node.left == null && node.right == null) {
                    return null;
                }
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }
                IntervalNode minNode = findMin(node.right);
                node.interval = minNode.interval;
                node.right = deleteHelper(node.right, minNode.interval);
            }
        }

        if (node != null) {
            node.maxEnd = Math.max(getMaxEnd(node.left), getMaxEnd(node.right));
        }

        return node;
    }

    private IntervalNode findMin(IntervalNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int getMaxEnd(IntervalNode node) {
        return (node == null) ? Integer.MIN_VALUE : node.maxEnd;
    }

    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        findOverlappingHelper(root, start, end, result);
        return result;
    }

    private void findOverlappingHelper(IntervalNode node, int start, int end, List<Interval> result) {
        if (node == null) {
            return;
        }

        if (node.interval.start <= end && node.interval.end >= start) {
            result.add(node.interval);
        }

        if (node.left != null && node.left.maxEnd >= start) {
            findOverlappingHelper(node.left, start, end, result);
        }

        if (node.right != null && node.interval.start <= end) {
            findOverlappingHelper(node.right, start, end, result);
        }
    }
}

public class Problem_9_Solution {
    public static void main(String[] args) {
        IntervalTree intervalTree = new IntervalTree();

        intervalTree.insertInterval(15, 20);
        intervalTree.insertInterval(10, 30);
        intervalTree.insertInterval(5, 15);
        intervalTree.insertInterval(17, 19);
        intervalTree.insertInterval(12, 15);
        intervalTree.insertInterval(30, 40);

        List<Interval> overlappingIntervals = intervalTree.findOverlappingIntervals(14, 17);
        System.out.println("Overlapping Intervals with [14, 17]:");
        for (Interval interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }

        intervalTree.deleteInterval(12, 15);

        overlappingIntervals = intervalTree.findOverlappingIntervals(14, 17);
        System.out.println("\nOverlapping Intervals with [14, 17] after deletion:");
        for (Interval interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}
