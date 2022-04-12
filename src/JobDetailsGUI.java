import Job.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static JobDetailsGUI j = new JobDetailsGUI();
    private DefaultListModel<SparePart> partModel;
    private Job job;

    public JobDetailsGUI(Job job) {
        this.job = job;
        jobIDLabel.setText(String.valueOf(job.getJobID()));

        // Have to call this here because it doesn't work in CreateUIComponents
        setComboBox();

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });

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
                                (String) statusBox.getSelectedItem())) {
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
                        controller.updateStock(((SparePart) partSelectBox.getSelectedItem()).getStock(), ((SparePart)  partSelectBox.getSelectedItem()).getPartID());
                    }
                    // If Stock is less than one
                    else {
                        JOptionPane.showMessageDialog(null, "This item's stock is depleted.");
                    }
                }
            }
        });
        deletePartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partList.getSelectedValue() != null) {
                    // Increment Object's stock
                    ((SparePart) partList.getSelectedValue()).setStock(((SparePart) partList.getSelectedValue()).getStock() + 1);

                    // Update database to Object's incremented stock
                    Job_Controller controller = new Job_Controller();
                    controller.updateStock(((SparePart) partList.getSelectedValue()).getStock(), ((SparePart)  partList.getSelectedValue()).getPartID());

                    // Remove item from list last, so that selectedValue doesn't move
                    partModel.removeElement(partList.getSelectedValue());

                }
            }
        });
    }

    public JobDetailsGUI() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                ViewJobsGUI.main();
            }
        });
    }

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
        j.setPreferredSize(new Dimension(800,480));
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

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
            regNoField = new JTextField();
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
            regNoField = new JTextField();
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
