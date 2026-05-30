package window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.User;
import service.ApprovalService;

import java.awt.*;
import java.util.ArrayList;

//window na ginagamit ng admin pang approve
public class ApproveUsersFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTable usersTable; //displays pending accounts
    private DefaultTableModel tableModel; //manages table data
    private ApprovalService approvalService; //service class responsible for approval operations

    //constructor gui ng admin approval frame
    public ApproveUsersFrame() {
        approvalService = new ApprovalService(); //object ng ApprovalService

        setTitle("Approve Registered Accounts");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        //create table model with heathers
        tableModel = new DefaultTableModel(new String[] {"User ID", "Username", "Role", "Status"}, 0) {
            private static final long serialVersionUID = 1L;

            //prevents users from editing table
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        usersTable = new JTable(tableModel); //create table using table model
        add(new JScrollPane(usersTable), BorderLayout.CENTER); //adds scroll bar

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10)); //panel ng buttons
        JButton approveButton = new JButton("Approve Selected");
        JButton rejectButton = new JButton("Reject Selected");
        JButton refreshButton = new JButton("Refresh");

        //add buttons to panel
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //action buttons
        approveButton.addActionListener(e -> approveSelectedUser());
        rejectButton.addActionListener(e -> rejectSelectedUser());
        refreshButton.addActionListener(e -> loadPendingUsers());

        loadPendingUsers(); //load lahat ng pending users
    }

    //kukunin na lahat ng pending users sa database tapos ididisplay sya sa table
    private void loadPendingUsers() {
        tableModel.setRowCount(0); //clears existing table rows
        ArrayList<User> pendingUsers = approvalService.getPendingUsers(); //gets list of pending users

        //adds all pending users
        for (User user : pendingUsers) {
            tableModel.addRow(new Object[] {
                user.getUserId(),
                user.getUsername(),
                user.getRole(),
                user.getStatus()
            });
        }
    }

    //approves selected accounts
    private void approveSelectedUser() {
        int selectedRow = usersTable.getSelectedRow(); //gets selected row index

        if (selectedRow == -1) { //ensures row is selected
            JOptionPane.showMessageDialog(this, "Please select an account to approve.");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0); //get user information
        String username = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog( //ask confirmation
            this,
            "Approve account for " + username + "?",
            "Confirm Approval",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = approvalService.approveUser(userId); //updates acc status to approved

            if (success) {
                JOptionPane.showMessageDialog(this, "Account approved successfully.");
                loadPendingUsers(); //reload table pag may naapprove
            } else {
                JOptionPane.showMessageDialog(this, "Failed to approve account.");
            }
        }
    }

    //reject acc
    private void rejectSelectedUser() {
        int selectedRow = usersTable.getSelectedRow(); //get selected row index

        if (selectedRow == -1) { //ensures that row is selected
            JOptionPane.showMessageDialog(this, "Please select an account to reject.");
            return;
        }

        //get user information
        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Reject account for " + username + "? This will delete the pending account.",
            "Confirm Rejection",
            JOptionPane.YES_NO_OPTION
        );

        //ask confirmation
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = approvalService.rejectUser(userId); //delete pending account from database

            if (success) {
                JOptionPane.showMessageDialog(this, "Account rejected successfully.");
                loadPendingUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to reject account.");
            }
        }
    }
}