package MineSweeper.Frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MineSweeper.Components.IconProvider;
import MineSweeper.Components.IconProvider.GameIconSet;
import MineSweeper.RecordHandler.GameRecords;
import MineSweeper.Components.ImagePanel;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;

public class Records extends JDialog {

    private final ImagePanel contentPanel = new ImagePanel(Toolkit.getDefaultToolkit()
            .getImage(MainMenu.class.getResource("/MineSweeper/Images/DesignerView.png")));
    private JTable table;
    private JLabel lblNewLabel;
    static Records dialog;
    private JLabel lblNoItem;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            dialog = new Records();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Records() {
        setResizable(false);
        setTitle("Records");
        setBounds(100, 100, 463, 477);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        table = new JTable();
        table.setShowHorizontalLines(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(Color.WHITE);
        table.setRowHeight(25);
        table.setShowGrid(false);
        table.setShowVerticalLines(false);
        table.setOpaque(false);
        table.setSelectionForeground(new Color(51, 153, 255));
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Score", "Name"}) {
            Class[] columnTypes = new Class[] {
                    Integer.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                    false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        // Next 3 lines brings contents to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setOpaque(false);

        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        table.getColumnModel().getColumn(0).setPreferredWidth(126);
        table.getColumnModel().getColumn(1).setPreferredWidth(149);
        table.setBounds(10, 62, 427, 335);
        contentPanel.add(table);

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(IconProvider.getIconResource(GameIconSet.Score32));
        lblNewLabel.setBounds(397, 15, 32, 32);
        contentPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Scores");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setBounds(50, 15, 42, 14);
        contentPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("<html>\r\n<body><p style=\"margin-left:28px\">You can see the time " +
                "record of the games from this section</p></body>\r\n</html>");
        lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
        lblNewLabel_2.setBounds(14, 29, 413, 23);
        contentPanel.add(lblNewLabel_2);

        lblNoItem = new JLabel("No items have been added yet!");
        lblNoItem.setForeground(Color.DARK_GRAY);
        lblNoItem.setBounds(155, 197, 137, 14);
        contentPanel.add(lblNoItem);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Confirmation!");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                        dialog.dispose();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }

        loadRecords();
    }

    /**
     * Load records into view
     */
    void loadRecords(){
        if (!(GameRecords.getRecordItems().size() == 0)){
            lblNoItem.setVisible(false);
            for(int i = 0; i < GameRecords.getRecordItems().size(); i++){

                String name = GameRecords.getRecordItems().get(i).getName();
                int score = GameRecords.getRecordItems().get(i).getRecord();

                Object[] row = { score, name};

                DefaultTableModel model = (DefaultTableModel) table.getModel();

                model.addRow(row);
            }
        }
    }
}
