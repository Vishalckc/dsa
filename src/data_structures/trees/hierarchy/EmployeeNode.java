package data_structures.trees.hierarchy;

import java.util.ArrayList;
import java.util.List;

public class EmployeeNode {
    private String name;
    private List<EmployeeNode> children;

    public EmployeeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<EmployeeNode> getChildren() {
        return children;
    }

    public void addChild(EmployeeNode child) {
        this.children.add(child);
    }
}