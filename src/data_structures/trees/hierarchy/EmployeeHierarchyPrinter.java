package data_structures.trees.hierarchy;

import java.util.ArrayDeque;
import java.util.Deque;

public class EmployeeHierarchyPrinter {
    public static void main(String[] args) {
        EmployeeHierarchyPrinter parser = new EmployeeHierarchyPrinter();
        EmployeeNode root = parser.populateNodesDefault();
        //printAllNodes(root);
        //Print via Recursive DFS
        System.out.println(parser.getAllChildrenAsString(root, new StringBuilder()));
        //Print via Iterative BFS
        String res = parser.getAllEmployeesBFS(root);
        System.out.println(res);
    }

    private static String getAllChildrenAsString(EmployeeNode root, StringBuilder sb) {
        if (root == null) return "";
        if (sb.length() > 0) sb.append(", ");
        sb.append(root.getName());
        for (EmployeeNode employeeNode : root.getChildren()) {
            getAllChildrenAsString(employeeNode, sb);
        }
        return sb.toString().trim().substring(0, sb.length() - 2);
    }

    private String getAllEmployeesBFS(EmployeeNode root) {
        if (root == null) return "";
        StringBuilder result = new StringBuilder();
        Deque<EmployeeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            EmployeeNode employeeNode = q.poll();
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(employeeNode.getName());
            if (!employeeNode.getChildren().isEmpty()) {
                for (EmployeeNode c : employeeNode.getChildren()) {
                    q.offer(c);
                }
            }
        }
        return result.toString();

    }

    private static void printAllNodes(EmployeeNode root) {
        if (root == null) return;
        System.out.println(root.getName());
        root.getChildren().forEach(EmployeeHierarchyPrinter::printAllNodes);
    }

    private EmployeeNode populateNodesDefault() {
        EmployeeNode root = new EmployeeNode("CEO");
        EmployeeNode vp1 = new EmployeeNode("VP of Engineering");
        EmployeeNode vp2 = new EmployeeNode("VP of Sales");
        EmployeeNode eng1 = new EmployeeNode("Engineering Manager 1");
        EmployeeNode eng2 = new EmployeeNode("Engineering Manager 2");
        EmployeeNode sales1 = new EmployeeNode("Sales Manager 1");
        EmployeeNode sales2 = new EmployeeNode("Sales Manager 2");
        root.addChild(vp1);
        root.addChild(vp2);
        vp1.addChild(eng1);
        vp1.addChild(eng2);
        vp2.addChild(sales1);
        vp2.addChild(sales2);
        return root;
    }
}
