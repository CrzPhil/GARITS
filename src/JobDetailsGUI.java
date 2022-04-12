import Job.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Job.Job_Controller;
import Job.SparePart;
import Job.SQL_JobHelper;

public class JobDetailsGUI extends JFrame{
    private JPanel Main;
    private JButton returnButton;
    private JTextField typeField;
    private JTextField durationField;
    private JTextField priceField;
    private JTextField dateField;
    private JTextField requiredPartsField;
    private JTextField mileageField;
    private JTextField motField;
    private JTextField regNoField;
    private JTextArea additionalField;
    private JButton saveChangesButton;
    private JLabel jobIDLabel;
    private JLabel idLabel;
    private JComboBox statusBox;
    private JComboBox jobTypeBox;
    private JButton deleteButton;
    private JComboBox partSelectBox;
    private JButton addPartButton;
    private JButton deletePartButton;
    private JList partList;
    private JLabel regNoLabel;
    private JComboBox comboBox1;
    private JLabel mechanicBox;
    private static JobDetailsGUI j = new JobDetailsGUI();
    private DefaultListModel<SparePart> partModel;
    // partID -> Part; This is a collection of the originally added parts, that already exist in the Job_SpareParts Table
    private HashMap<Integer, SparePart> usedParts;
    // Current job object being edited
    private Job job;

    public JobDetailsGUI(Job job) {
        this.job = job;
        // Set the job ID Label (cannot be changed)
        jobIDLabel.setText(String.valueOf(job.getJobID()));
        regNoLabel.setText(job.getRegNo());
        // Have to call this here because it doesn't work in CreateUIComponents
        setComboBox();

        // Create cache of used Parts
        cacheParts();

        // Cancel edit
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restore cache state in Job_SpareParts table
                Job_Controller controller = new Job_Controller();

                // Iterate through items in List, releasing them from the HashMap (as they weren't deleted in the first place)
                // Release items that were in the list (incrementing their stock)
                for(int i = 0; i< partList.getModel().getSize();i++){
                    SparePart tmp = ((SparePart) partList.getModel().getElementAt(i));
                    // Increment Object's Stock
                    tmp.setStock(tmp.getStock() + 1);
                    // Increment DB's Stock
                    controller.updateStock(tmp.getStock(), tmp.getPartCode());

                    // Remove the part from the HashMap, as it does not have to be re-generated
                    usedParts.remove(tmp.getPartID());
                }

                // All remaining items in HashMap have to be added to Job_SpareParts again
                for (Map.Entry<Integer, SparePart> set : usedParts.entrySet()) {
                    controller.addToJob(job.getJobID(), set.getValue().getPartCode());
                }

                j.dispose();
                ViewJobsGUI.main();
            }
        });

        // Delete JOB
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pop-up asking for confirmation
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to make these changes?", "Confirm changes", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Check input
                    // Update row in database
                    SQL_JobHelper helper = new SQL_JobHelper();
                    helper.deleteJob(job.getJobID());
                    // Successful Job update
                    JOptionPane.showMessageDialog(null, "Job Deleted successfully.");
                    j.dispose();
                    ViewJobsGUI.main();

                    }
                    // If input invalid
                    else {
                        JOptionPane.showMessageDialog(null, "Please verify your input and try again.");
                    }
                }
        });

        // Commit changes to Database
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pop-up asking for confirmation
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to make these changes?", "Confirm changes", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Check input
                    if (inputValid(typeField.getText(),
                            durationField.getText(),
                            mileageField.getText(),
                            priceField.getText())) {
                        // Update row in database
                        SQL_JobHelper helper = new SQL_JobHelper();
                        if (helper.updateJob(
                                job.getJobID(),
                                (String) jobTypeBox.getSelectedItem(),
                                Float.parseFloat(durationField.getText()),
                                dateField.getText(),
                                requiredPartsField.getText(),
                                motField.getText(),
                                Integer.parseInt(mileageField.getText()),
                                Float.parseFloat(priceField.getText()),
                                additionalField.getText(),
                                (String) statusBox.getSelectedItem(),
                                regNoLabel.getText())) {

                            // Iterate through list for each Spare Part and add it to the job
                            for(int i = 0; i< partList.getModel().getSize();i++) {
                                // Current part in the list
                                SparePart tmp = ((SparePart) partList.getModel().getElementAt(i));

                                // Only if the part is NOT in the cache already, we add it to the job
                                // This avoids duplication, if for instance we don't even change the parts used
                                // This routine runs and sees that all parts in the list are already in the HashMap,
                                // Therefore it does not add them to the DB.
                                if (!usedParts.containsKey(tmp.getPartID())) {
                                    helper.addToJob(job.getJobID(), tmp.getPartCode());
                                }
                            }

                            // Successful Job update
                            JOptionPane.showMessageDialog(null, "Job updated successfully.");
                            j.dispose();
                            ViewJobsGUI.main();
                        } else {
                            JOptionPane.showMessageDialog(null, "Something went wrong.\n Double-check your values.");
                        }
                    }
                    // If input invalid
                    else {
                        JOptionPane.showMessageDialog(null, "Please verify your input and try again.");
                    }
                }
            }
        });

        // TODO: Make this more readable
        // Add PART to LIST
        addPartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partSelectBox.getSelectedItem() != null) {
                    if (((SparePart) partSelectBox.getSelectedItem()).getStock() >= 1) {
                        // Add Item to JList
                        partModel.addElement((SparePart) partSelectBox.getSelectedItem());
                        // Update part Object
                        ((SparePart) partSelectBox.getSelectedItem()).setStock(((SparePart) partSelectBox.getSelectedItem()).getStock() - 1);

                        // Update database
                        Job_Controller controller = new Job_Controller();
                        // Decrement stock
                        controller.updateStock(((SparePart) partSelectBox.getSelectedItem()).getStock(), ((SparePart)  partSelectBox.getSelectedItem()).getPartCode());
                    }
                    // If Stock is less than one
                    else {
                        JOptionPane.showMessageDialog(null, "This item's stock is depleted.");
                    }
                }
            }
        });

        // Delete PART from LIST
        deletePartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partList.getSelectedValue() != null) {
                    // Current selected value in JList
                    SparePart tmp = ((SparePart) partList.getSelectedValue());
                    // Increment Object's stock
                    tmp.setStock(tmp.getStock() + 1);

                    // Update database to Object's incremented stock
                    Job_Controller controller = new Job_Controller();
                    controller.updateStock(tmp.getStock(), tmp.getPartCode());

                    if (usedParts.containsKey(tmp.getPartID())) {
                        // Deleting a value means we have to delete it from the Job_SpareParts, even if it gets added back again
                        // If the job gets cancelled, we can just re-add them using the HashMap cache
                        controller.deleteJobPart(tmp.getPartID());
                    }

                    // Remove item from list last, so that selectedValue doesn't move
                    partModel.removeElement(partList.getSelectedValue());

                }
            }
        });
    }

    // Default constructor simply exists so the frame can be created in an empty state
    public JobDetailsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
    }

    /**
     * @param type Type specified in input field
     * @param duration Duration specified in input field
     * @param mileage Mileage specified in input field
     * @param price Price specified in input field
     * @return Whether user input is valid (bool)
     */
    private boolean inputValid(String type, String duration, String mileage, String price) {
        // Check if Type is valid
        if (!job.getJobTypes().contains(type)) {
            return false;
        }
        // Check for number formats
        try {
            Float.parseFloat(duration);
            Float.parseFloat(price);
            Integer.parseInt(mileage);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(Job job){
        j.setContentPane(new JobDetailsGUI(job).Main);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setTitle("Chosen Job Details");
        Image icon = Toolkit.getDefaultToolkit().getImage("data/logo.png");
        j.setIconImage(icon);
        j.setPreferredSize(new Dimension(1000,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

    /**
     * Helper function to create Dropdown menu of spare parts.
     */
    private void setComboBox() {
        // Configure which option to show
        String other;
        String otherother;
        if (Objects.equals(job.getJobType(), Job.types[0])) {
            other = Job.types[1];
            otherother = Job.types[2];
        }  else  if (Objects.equals(job.getJobType(), Job.types[1])) {
            other = Job.types[0];
            otherother = Job.types[2];
        } else {
            other = Job.types[0];
            otherother = Job.types[1];
        }

        jobTypeBox.addItem(job.getJobType());
        jobTypeBox.addItem(other);
        jobTypeBox.addItem(otherother);

    }

    /**
     * Add Parts originally assigned to job to the HashMap, so they can later be released/deleted/restored
     * All SparePart objects in the hashmap usedParts exist in the Job_SparePart Table.
     * If we alter the spare parts used on the job, and later save, we do not want to duplicate these already existing values.
     * Same if we choose to delete the spare parts and then cancel the edit, we have to know what to restore.
     */
    private void cacheParts() {
        Job_Controller controller = new Job_Controller();
        SparePart[] parts = controller.getJobParts(this.job.getJobID());

        // If we put this at the beginning of the program where usedParts is defined, nothing is rendered
        // Most bizarre IntelliJ bug ever. Spent 2 hours trying to figure out what is causing the issue.
        // And it is the creation of the hashmap.
        usedParts =  new HashMap<Integer, SparePart>();

        // Add part to hashmap
        for (SparePart part : parts) {
            usedParts.put(part.getPartID(), part);
        }
    }

    // TODO: Registration Number ?
    private void createUIComponents() {
        if (job != null) {
            // For some reason label does not get updated here, and we have to repeat this in the constructor
            // jobID is not modifiable, as it is a PK in the database and has to be unique
            jobIDLabel = new JLabel(String.valueOf(job.getJobID()));

            // Create text fields
            typeField = new JTextField(job.getJobType());
            priceField = new JTextField(String.valueOf(job.getPrice()));
            motField = new JTextField(job.getMotNO());
            regNoLabel = new JLabel(job.getRegNo());
            dateField = new JTextField(job.getDates());
            durationField = new JTextField(String.valueOf(job.getDuration()));
            requiredPartsField = new JTextField(job.getParts());
            mileageField = new JTextField(String.valueOf(job.getMileage()));

            // Create combobox for status

            // Configure which option to show
            String otherOption;
            if (Objects.equals(job.getStatus(), Job.getStates()[0])) {
                otherOption = Job.getStates()[1];
            } else {
                otherOption = Job.getStates()[0];
            }

            statusBox = new JComboBox<>(new String[]{job.getStatus(), otherOption});

            jobTypeBox = new JComboBox<String>();

            // Controller to get spare parts
            Job_Controller controller = new Job_Controller();

            // Configure List of added parts
            this.partModel = new DefaultListModel<SparePart>();

            // Get Parts assigned to job
            SparePart[] addedParts = controller.getJobParts(job.getJobID());

            // Add parts to model
            for (SparePart part : addedParts) {
                partModel.addElement(part);
            }

            partList = new JList<>(partModel);
            partList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Dropdown of possible parts to add to job
            // TODO: filter this to become vehicle-specific (manufacturer/model)
            partSelectBox = new JComboBox<>(controller.getAllParts());

        }
        else {
            jobIDLabel = new JLabel();
            typeField = new JTextField();
            priceField = new JTextField();
            motField = new JTextField();
            regNoLabel = new JLabel();
            dateField = new JTextField();
            durationField = new JTextField();
            requiredPartsField = new JTextField();
            mileageField = new JTextField();
            statusBox = new JComboBox<String>();
            jobTypeBox = new JComboBox<String>();
            partList = new JList();
            partSelectBox = new JComboBox();
        }
    }
}
