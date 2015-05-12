/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import MQApi.Enums.ChannelType;
import MQApi.Enums.LogType;
import MQApi.Enums.MQObjectType;
import MQApi.Enums.QueueType;
import MQApi.Enums.StatusType;
import MQApi.Logs.LogWriter;
import MQApi.MQTest;
import MQApi.Models.Query.ConnectionDetailModel;
import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQCommandResult;
import MQApi.QueryModel.MQQueryResultBase;
import MQApi.QueryModel.MQQueueListResult;
import MQApi.TextWriter;
import MQApi.Utilities.MQUtility;
import Tasks.*;
import Tasks.TaskInterface.StopableTask;
import UI.Dialogs.BackupRestoreMessageDialog;
import UI.Dialogs.BrowseMessageDialog;
import UI.Dialogs.ChannelProperitiesDialog;
import UI.Dialogs.StatusDialog;
import UI.Dialogs.ClearMessagesDialog;
import UI.Dialogs.DialogFactory;
import UI.Dialogs.MessageEditDialog;;
import UI.Dialogs.QueueProperitiesDialog;
import UI.Dialogs.ResetChannelDialog;
import UI.Dialogs.ResolveChannelDialog;
import UI.Dialogs.StopChannelDialog;
import UI.Helpers.*;
import UI.Misc.CustomTableCellRender;
import UI.Misc.CustomTreeRender;
import UI.Misc.ExceptionHandler;
import UI.Models.*;
import UI.ReferenceObjects.ToolStatusReference;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.headers.MQDataException;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.OptionalDataException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;import UI.Dialogs.QueueProperitiesDialog;
import UI.Dialogs.ResetChannelDialog;
import UI.Dialogs.ResolveChannelDialog;
import UI.Dialogs.StopChannelDialog;
import UI.Helpers.*;
import UI.Misc.CustomTableCellRender;
import UI.Misc.CustomTreeRender;
import UI.Misc.ExceptionHandler;
import UI.Models.*;
import UI.ReferenceObjects.ToolStatusReference;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.headers.MQDataException;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.OptionalDataException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jzhou
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    ToolStatusReference chToolRef = new ToolStatusReference();
    private ArrayList<ConnectionDetailModel> ConnectionDetailModels;
    private final String SettingFilePath = "ConnectionSettings.xml";
    private boolean queueManagerEditPanelEditMode = false;
    //private Timer timer;
    IconManager iconManager = new IconManager();
    private String activedSearchString = "";
    StopableTask CurrentUpdateTask= null;
    public MainWindow() {      
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2 - 450, dim.height/2-this.getSize().height/2 - 350);
        UIManager.put("ProgressBar.background",Color.YELLOW);
        //UIManager.put("ProgressBar.foreground",Color.RED);
        initComponents();
        initCustomProperties();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConnectionDetailDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        PortTextBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        HostTextBox = new javax.swing.JTextField();
        QueueManagerTextBox = new javax.swing.JTextField();
        ChannelTextBox = new javax.swing.JTextField();
        AddtButton = new javax.swing.JButton();
        CancelBitton = new javax.swing.JButton();
        SplitPane = new javax.swing.JSplitPane();
        TreeViewPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TreeView = new javax.swing.JTree();
        ContentPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ContentTable = new javax.swing.JTable(){
            @Override
            public Class getColumnClass(int column)
            {
                int rowCount = getRowCount();
                Object value = null;
                for(int i = 0 ; i < rowCount; i++){
                    value = getValueAt(i, column);
                    if(value != null){
                        return value.getClass();
                    }
                }
                return Object.class;
            }
        };
        FilterPanel = new javax.swing.JPanel();
        FilterToolbar = new javax.swing.JToolBar();
        RefreshButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        ShowSystemObjectToggleButton = new javax.swing.JToggleButton();
        ShowTempObjectToggle = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        SearchButton = new javax.swing.JButton();
        SearchTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        ContentTableProgressBar = new javax.swing.JProgressBar();
        UpdateTimeStampLabel = new javax.swing.JLabel();
        MainMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        TestMenuItem = new javax.swing.JMenuItem();
        MainWindowMenu = new javax.swing.JMenu();
        ChannelStatusToolMenuItem = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        AboutMenuItem = new javax.swing.JMenuItem();

        ConnectionDetailDialog.setMinimumSize(new java.awt.Dimension(350, 210));
        ConnectionDetailDialog.setModal(true);
        ConnectionDetailDialog.setResizable(false);

        jLabel1.setText("Host:");

        jLabel2.setText("Queue Manager:");

        jLabel3.setText("Channel:");

        jLabel4.setText("Port:");

        AddtButton.setText("Ok");
        AddtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddtButtonActionPerformed(evt);
            }
        });

        CancelBitton.setText("Cancel");
        CancelBitton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBittonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConnectionDetailDialogLayout = new javax.swing.GroupLayout(ConnectionDetailDialog.getContentPane());
        ConnectionDetailDialog.getContentPane().setLayout(ConnectionDetailDialogLayout);
        ConnectionDetailDialogLayout.setHorizontalGroup(
            ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ChannelTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                        .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(HostTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(QueueManagerTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                                        .addComponent(AddtButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CancelBitton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(PortTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ConnectionDetailDialogLayout.setVerticalGroup(
            ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectionDetailDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(HostTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(QueueManagerTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ChannelTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PortTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ConnectionDetailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddtButton)
                    .addComponent(CancelBitton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 700));
        setPreferredSize(new java.awt.Dimension(900, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        TreeViewPanel.setPreferredSize(new java.awt.Dimension(200, 466));

        TreeView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeViewMouseClicked(evt);
            }
        });
        TreeView.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                TreeViewValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(TreeView);

        javax.swing.GroupLayout TreeViewPanelLayout = new javax.swing.GroupLayout(TreeViewPanel);
        TreeViewPanel.setLayout(TreeViewPanelLayout);
        TreeViewPanelLayout.setHorizontalGroup(
            TreeViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(TreeViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
        );
        TreeViewPanelLayout.setVerticalGroup(
            TreeViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
            .addGroup(TreeViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
        );

        SplitPane.setLeftComponent(TreeViewPanel);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 440));

        ContentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        ContentTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        ContentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ContentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ContentTable);

        FilterPanel.setPreferredSize(new java.awt.Dimension(338, 35));

        FilterToolbar.setFloatable(false);
        FilterToolbar.setRollover(true);
        FilterToolbar.setPreferredSize(new java.awt.Dimension(100, 35));
        FilterToolbar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        RefreshButton.setIcon(iconManager.RefreshIcon());
        RefreshButton.setToolTipText("Refresh");
        RefreshButton.setFocusable(false);
        RefreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RefreshButton.setPreferredSize(new java.awt.Dimension(25, 25));
        RefreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RefreshButtonMouseClicked(evt);
            }
        });
        FilterToolbar.add(RefreshButton);
        FilterToolbar.add(jSeparator1);

        ShowSystemObjectToggleButton.setIcon(iconManager.Root());
        ShowSystemObjectToggleButton.setToolTipText("Show system objects");
        ShowSystemObjectToggleButton.setFocusable(false);
        ShowSystemObjectToggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ShowSystemObjectToggleButton.setPreferredSize(new java.awt.Dimension(25, 25));
        ShowSystemObjectToggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ShowSystemObjectToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShowSystemObjectToggleButtonItemStateChanged(evt);
            }
        });
        FilterToolbar.add(ShowSystemObjectToggleButton);

        ShowTempObjectToggle.setIcon(iconManager.TemporaryObjectIcon());
        ShowTempObjectToggle.setToolTipText("Show temporary objects");
        ShowTempObjectToggle.setFocusable(false);
        ShowTempObjectToggle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ShowTempObjectToggle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ShowTempObjectToggle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShowTempObjectToggleItemStateChanged(evt);
            }
        });
        FilterToolbar.add(ShowTempObjectToggle);
        FilterToolbar.add(jSeparator2);

        SearchButton.setIcon(iconManager.SearchIcon());
        SearchButton.setToolTipText("Search");
        SearchButton.setFocusable(false);
        SearchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SearchButton.setPreferredSize(new java.awt.Dimension(25, 25));
        SearchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchButtonMouseClicked(evt);
            }
        });
        FilterToolbar.add(SearchButton);

        SearchTextField.setMaximumSize(new java.awt.Dimension(250, 40));
        SearchTextField.setMinimumSize(new java.awt.Dimension(250, 40));
        SearchTextField.setPreferredSize(new java.awt.Dimension(250, 40));
        FilterToolbar.add(SearchTextField);

        javax.swing.GroupLayout FilterPanelLayout = new javax.swing.GroupLayout(FilterPanel);
        FilterPanel.setLayout(FilterPanelLayout);
        FilterPanelLayout.setHorizontalGroup(
            FilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FilterToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FilterPanelLayout.setVerticalGroup(
            FilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FilterPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(FilterToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setMinimumSize(new java.awt.Dimension(346, 35));

        ContentTableProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

        UpdateTimeStampLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UpdateTimeStampLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ContentTableProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ContentTableProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateTimeStampLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout ContentPanelLayout = new javax.swing.GroupLayout(ContentPanel);
        ContentPanel.setLayout(ContentPanelLayout);
        ContentPanelLayout.setHorizontalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
            .addComponent(FilterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContentPanelLayout.setVerticalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPanelLayout.createSequentialGroup()
                .addComponent(FilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        SplitPane.setRightComponent(ContentPanel);

        FileMenu.setText("File");

        TestMenuItem.setText("Test");
        TestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(TestMenuItem);

        MainMenuBar.add(FileMenu);

        MainWindowMenu.setText("Tools");

        ChannelStatusToolMenuItem.setText("Channel Status Tool");
        ChannelStatusToolMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChannelStatusToolMenuItemActionPerformed(evt);
            }
        });
        MainWindowMenu.add(ChannelStatusToolMenuItem);

        MainMenuBar.add(MainWindowMenu);

        HelpMenu.setText("Help");

        AboutMenuItem.setText("About");
        HelpMenu.add(AboutMenuItem);

        MainMenuBar.add(HelpMenu);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SplitPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SplitPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
// Popup menu creation    
    private JPopupMenu getTreeViewQueueManagerFolderPopupMenu(){
        JPopupMenu popup = new JPopupMenu();
        JMenuItem addQueueManageMenuItem = new JMenuItem("Add Queue Manager",iconManager.DeviceManager());
        addQueueManageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queueManagerEditPanelEditMode = false;
                addQueueManageMenuItemActionPerformed(evt);
            }
        });
        popup.add(addQueueManageMenuItem);
        
        return popup;
    } 
    
    private JPopupMenu getTreeViewQueuePopupMenu(){
        JPopupMenu popup = new JPopupMenu();
        JMenuItem exportQueuePropertiesMenuItem = new JMenuItem("Export queue properties",iconManager.CSVIcon());
        JMenu CreateQueueMenu = new JMenu("Create");
        CreateQueueMenu.setIcon(iconManager.AddNewIcon());
        JMenuItem CreateLocalQueueMenuItem = new JMenuItem("Local queue", iconManager.Queue());
        JMenuItem CreateRemoteQueueMenuItem = new JMenuItem("Remote queue", iconManager.Queue());
        JMenuItem CreateAliasQueueMenuItem = new JMenuItem("Alias queue", iconManager.Queue());
        JMenuItem CreateModelQueueMenuItem = new JMenuItem("Model queue", iconManager.Queue());
        CreateLocalQueueMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createQueue(QueueType.Local);
            }
        });
        CreateRemoteQueueMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createQueue(QueueType.Remote);
            }
        });
        CreateAliasQueueMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createQueue(QueueType.Alias);
            }
        });
        CreateModelQueueMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createQueue(QueueType.Model);
            }
        });
        exportQueuePropertiesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportProperties(MQObjectType.Queue);
            }
        });
        CreateQueueMenu.add(CreateLocalQueueMenuItem);
        CreateQueueMenu.add(CreateRemoteQueueMenuItem);
        CreateQueueMenu.add(CreateAliasQueueMenuItem);
        CreateQueueMenu.add(CreateModelQueueMenuItem);
        popup.add(CreateQueueMenu);
        popup.add(exportQueuePropertiesMenuItem);
        return popup;
    }
  
    private JPopupMenu getTreeViewChannelPopupMenu(){
        JPopupMenu popup = new JPopupMenu();
        JMenuItem exportQueuePropertiesMenuItem = new JMenuItem("Export channel properties",iconManager.CSVIcon());
        JMenuItem exportQueueStatusMenuItem = new JMenuItem("Export channel status",iconManager.CSVIcon());
        JMenu CreateChannelMenu = new JMenu("Create");
        CreateChannelMenu.setIcon(iconManager.AddNewIcon());
        JMenuItem CreateSenderChannelMenuItem = new JMenuItem("Sender channel", iconManager.Channel());
        JMenuItem CreateReceiverChannelMenuItem = new JMenuItem("Receiver channel", iconManager.Channel());
        JMenuItem CreateServerChannelMenuItem = new JMenuItem("Server channel", iconManager.Channel());
        JMenuItem CreateRequesterChannelMenuItem = new JMenuItem("Requester channel", iconManager.Channel());
        JMenuItem CreateServerConnectionChannelMenuItem = new JMenuItem("Server connection channel", iconManager.Channel());
        JMenuItem CreateClusterSenderMenuItem = new JMenuItem("Cluster-sender", iconManager.Channel());
        JMenuItem CreateClusterReceiverMenuItem = new JMenuItem("Cluster-receiver", iconManager.Channel());
        exportQueuePropertiesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportProperties(MQObjectType.Channel);
            }
        });
        exportQueueStatusMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportProperties(MQObjectType.ChannelStatus);
            }
        });
        CreateSenderChannelMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChannel(ChannelType.Sender);
            }
        });
        CreateReceiverChannelMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChannel(ChannelType.Receiver);
            }
        });
        CreateServerChannelMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChannel(ChannelType.Server);
            }
        });
        CreateRequesterChannelMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChannel(ChannelType.Requester);
            }
        });
        CreateServerConnectionChannelMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChannel(ChannelType.Server_Connection);
            }
        });
        CreateClusterSenderMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChannel(ChannelType.Cluster_Sender);
            }
        });
        CreateClusterReceiverMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChannel(ChannelType.Cluster_Receiver);
            }
        });
        CreateChannelMenu.add(CreateSenderChannelMenuItem);
        CreateChannelMenu.add(CreateReceiverChannelMenuItem);
        CreateChannelMenu.add(CreateServerChannelMenuItem);
        CreateChannelMenu.add(CreateRequesterChannelMenuItem);
        CreateChannelMenu.add(CreateServerConnectionChannelMenuItem);
        CreateChannelMenu.add(CreateClusterSenderMenuItem);
        CreateChannelMenu.add(CreateClusterReceiverMenuItem);
        popup.add(CreateChannelMenu);
        popup.add(exportQueuePropertiesMenuItem);
         popup.add(exportQueueStatusMenuItem);
        return popup;
    }
    
    private JPopupMenu getTreeViewQueueManagerPopupMenu(boolean isConnected){
        JPopupMenu popup = new JPopupMenu();
        JMenuItem connectMenuItem = new JMenuItem("Connect", iconManager.QueueMgrConnectedIcon());
        JMenuItem disconnectMenuItem = new JMenuItem("Disconnect",iconManager.QueueMgrDisconnectedIcon());
        JMenuItem editMenuItem = new JMenuItem("Edit",iconManager.PropertiesIcon());
        JMenuItem deleteMenuItem = new JMenuItem("Delete",iconManager.Delete());
        
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    TreeHelper.AddConnectedQueueManagerNode(TreeView);
                } catch (MQException ex) {
                    LogWriter.WriteToLog("MainWindow", "getTreeViewQueueManagerPopupMenu", ex);
                    showErrorMessage(MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()));
                }
            }
        });
        disconnectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    TreeHelper.DisconnectQueueManager(TreeView);
                } catch (MQException ex) {
                    LogWriter.WriteToLog("MainWindow", "getTreeViewQueueManagerPopupMenu", ex);
                    showErrorMessage(ex.getMessage());
                }
            }
        });
        editMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editQueueManager();
            }
        });
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteQueueManagerNode();
            }
        });
        
        if(!isConnected){
            popup.add(connectMenuItem);
            popup.add(editMenuItem);
            popup.add(deleteMenuItem);          
        }
        else{
            popup.add(disconnectMenuItem);
        }        
        return popup;
    } 
        
    private JPopupMenu getQueueListTablePopupMenu(Object type){
        if(type instanceof QueueType){
            QueueType queueType = (QueueType)type;
            
            JPopupMenu popup = new JPopupMenu();
            JMenuItem putMenuItem = new JMenuItem("Put Message", iconManager.PutMessageIcon());
            JMenuItem browseMsgMenuItem = new JMenuItem("Browse Messages", iconManager.BrowseMessageIcon());
            JMenuItem deleteMsgMenuItem = new JMenuItem("Clear messages",iconManager.DeleteMessageIcon());
            JMenuItem propertistMenuItem = new JMenuItem("Properties",iconManager.PropertiesIcon());
            JMenuItem deleteMenuItem = new JMenuItem("Delete queue",iconManager.Delete());
            JMenuItem backupMessageMenuItem = new JMenuItem("Backup messages to file", iconManager.BackupMessageIcon());
            JMenuItem restoreMessageMenuItem = new JMenuItem("Restore messages from file",iconManager.RestoreMessageIcon());
            JMenuItem queueHandleStatusMenuItem = new JMenuItem("Queue handle status",iconManager.StatusIcon());
            JMenuItem queueStatusMenuItem = new JMenuItem("Queue status",iconManager.StatusIcon());
            putMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showPutMessageDialog();
                }
            });
            browseMsgMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showBrowseMessageDialog();
                }
            });
            deleteMsgMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showClearMessageDialog();
                }
            });
            backupMessageMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showBackUpRestoreMessageDialog(true);
                }
            });
            restoreMessageMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showBackUpRestoreMessageDialog(false);
                }
            });
            propertistMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showSetupQueuePropertiesDialog();
                }
            });
            deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteQueue();
                }
            });
            queueHandleStatusMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showStatusDialog(StatusType.QueueHandleStatus);
                }
            });
            queueStatusMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showStatusDialog(StatusType.QueueStatus);
                }
            });
            switch(queueType){
                case Local:                   
                    popup.add(putMenuItem);
                    popup.add(browseMsgMenuItem);
                    popup.add(backupMessageMenuItem);
                    popup.add(restoreMessageMenuItem);
                    popup.add(deleteMsgMenuItem);
                    popup.add(new JPopupMenu.Separator());
                    popup.add(deleteMenuItem);
                    popup.add(new JPopupMenu.Separator()); 
                    popup.add(queueStatusMenuItem);
                    popup.add(queueHandleStatusMenuItem);
                    popup.add(new JPopupMenu.Separator());                    
                    popup.add(propertistMenuItem);
                    break;
                case Remote:
                    popup.add(putMenuItem);
                    popup.add(new JPopupMenu.Separator());
                    popup.add(deleteMenuItem);
                    popup.add(new JPopupMenu.Separator());
                    popup.add(propertistMenuItem);
                    break;
                case Alias:
                    popup.add(putMenuItem);
                    popup.add(browseMsgMenuItem);
                    popup.add(backupMessageMenuItem);
                    popup.add(restoreMessageMenuItem);
                    popup.add(deleteMsgMenuItem);
                    popup.add(new JPopupMenu.Separator());
                    popup.add(deleteMenuItem);
                    popup.add(new JPopupMenu.Separator());
                    popup.add(propertistMenuItem);
                    break;
                case Model:
                    popup.add(deleteMenuItem);
                    popup.add(new JPopupMenu.Separator());
                    popup.add(propertistMenuItem);
                    break;
            }

            return popup;       
        }
        return null;
    }
    
    private JPopupMenu getChannelListTablePopupMenu(Object type){
        if(type instanceof ChannelType){
            ChannelType channelType = (ChannelType)type;
            
            JPopupMenu popup = new JPopupMenu();
            JMenuItem startMenuItem = new JMenuItem("Start", iconManager.StartIcon());
            JMenuItem stopMenuItem = new JMenuItem("Stop", iconManager.StopIcon());
            JMenuItem resetMenuItem = new JMenuItem("Reset", iconManager.RefreshIcon());
            JMenuItem resolveMenuItem = new JMenuItem("Resolve", iconManager.ResolveIcon());
            JMenuItem deleteMenuItem = new JMenuItem("Delete", iconManager.Delete());
            JMenuItem propertiesMenuItem = new JMenuItem("Properties",iconManager.PropertiesIcon());
            JMenuItem statusMenuItem = new JMenuItem("Channel status",iconManager.StatusIcon());
            JMenuItem statusSavedMenuItem = new JMenuItem("Channel saved status",iconManager.StatusIcon());
            stopMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showStopChannelDialog();
                }
            });
            propertiesMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showSetupChannelPropertiesDialog();
                }
            });
            startMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    startChannel();
                }
            });
            resetMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showResetChannelDialog();
                }
            });
            resolveMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showResolveChannelDialog();
                }
            });
            deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteChannel();
                }
            });           
            statusMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                   showStatusDialog(StatusType.ChannelStatus);
                }
            });
            statusSavedMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                   showStatusDialog(StatusType.ChannelSavedStatus);
                }
            });
            popup.add(startMenuItem);
            popup.add(stopMenuItem);
            popup.add(new JPopupMenu.Separator());
            switch(channelType){
                case Cluster_Receiver:
                case Receiver:
                case Requester:
                    popup.add(resetMenuItem);
                    break;
                case Cluster_Sender:
                case Sender:
                case Server:
                    popup.add(resetMenuItem);
                    popup.add(resolveMenuItem);
                    break;
                case Server_Connection:
                    break;
            }
            popup.add(deleteMenuItem);
            popup.add(new JPopupMenu.Separator());
            popup.add(statusMenuItem);
            popup.add(statusSavedMenuItem);
            popup.add(new JPopupMenu.Separator());           
            popup.add(propertiesMenuItem);
            return popup; 
        }
        return null;
        
              
    }

 // Private methods
    
    private void initCustomProperties(){
        this.setIconImage(iconManager.Root().getImage()); 
        this.setTitle("MQ Admin Tool");
        //this.timer = new Timer();
        this.TreeViewPanel.setMinimumSize(new Dimension(200,400));
        this.SplitPane.setDividerLocation(200);
        this.HostTextBox.setText("");
        this.QueueManagerTextBox.setText("");
        this.ChannelTextBox.setText("SYSTEM.ADMIN.SVRCONN");
        this.PortTextBox.setText("1414");
        TreeHelper.InitTreeView(TreeView);
        TableHelper.InitTable(ContentTable);
        ConnectionDetailModels = XMLHelper.ReadConnectionModelFromXml(SettingFilePath);
        initQueueManagerSetting();
        
        this.ContentTableProgressBar.setIndeterminate(true);
        showContentTableLoading(false);
        this.ShowTempObjectToggle.setVisible(false);
        
        TreeView.setCellRenderer(new CustomTreeRender());
        //ContentTable.setDefaultRenderer(TableListObject.class, new CustomTableCellRender());      
        
    }
    
    private void initQueueManagerSetting(){
        for(ConnectionDetailModel connectionDetail : ConnectionDetailModels){
            TreeHelper.AddQueueManagerNodeToTreeView(TreeView, connectionDetail);
        }
    }
    
    private ConnectionDetailModel GetConnectionDetailModel(){
        ConnectionDetailModel connection = new ConnectionDetailModel();
        connection.Channel = this.ChannelTextBox.getText();
        connection.Host = this.HostTextBox.getText();
        try{
            int portNum = Integer.parseInt(this.PortTextBox.getText());
            connection.Port = Integer.toString(portNum);
        }
        catch (Exception ex){
           connection.Port = "1414"; 
        }
        connection.QueueManager = this.QueueManagerTextBox.getText();  
        return connection;
    }    
    
    private void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Error",JOptionPane.ERROR_MESSAGE );
    }
    
    private void editQueueManager(){
        ConnectionDetailModel model = TreeHelper.GetCurrentSelectedConnectionDetail(TreeView);
        if(model != null){
            queueManagerEditPanelEditMode = true;
           this.HostTextBox.setText(model.Host);
           this.QueueManagerTextBox.setText(model.QueueManager);
           this.ChannelTextBox.setText(model.Channel);
           this.PortTextBox.setText(model.Port);
           this.ConnectionDetailDialog.setLocationRelativeTo(this);
           ConnectionDetailDialog.setVisible(true);
        }
    }
    
    private void deleteQueueManagerNode(){
        ConnectionDetailModel deletedModel =  TreeHelper.DeleteQueueManagerNodeFromTreeView(TreeView);
        ConnectionDetailModels.remove(deletedModel);
        XMLHelper.WriteConnectionModelToXml(ConnectionDetailModels, SettingFilePath);
    }

    private boolean CheckQueueManagerConnection(DefaultMutableTreeNode queueManagerNode){
        return true;
    }
    
    private void resetTimmer(){
//        this.timer.cancel();
//        this.timer = new Timer();
    }
    
    private void showPutMessageDialog(){
        //PutMessageDialog dialog = DialogFactory.CreateOperationalDialog(PutMessageDialog.class, this, false ,TreeView, ContentTable);
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        String queueName = TableHelper.GetCurrentTableSelectRowObject(ContentTable).ObjectName;
        MessageEditDialog dialog = DialogFactory.CreateMessageNewDialog(this, true, queueManager,queueName);
        dialog.AddDialogActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void showBrowseMessageDialog(){  
        BrowseMessageDialog dialog = DialogFactory.CreateOperationalDialog(BrowseMessageDialog.class, this, false ,TreeView, ContentTable);
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void showClearMessageDialog(){
        ClearMessagesDialog dialog = DialogFactory.CreateOperationalDialog(ClearMessagesDialog.class, this, false ,TreeView, ContentTable);
        dialog.AddDialogActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);        
    }
    
    private void showBackUpRestoreMessageDialog(boolean isBackup){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(ContentTable);
        BackupRestoreMessageDialog dialog = DialogFactory.CreateBackupRestoreMessageDialog(this, true,queueManager, selectedObject, null);
        dialog.SetUsage(isBackup);
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true); 
    }
       
    private void showTableData(boolean loadNewData){
        TreePath path = TreeView.getSelectionPath();
        if(path != null){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            TreeNodeObject nodeObject = (TreeNodeObject)node.getUserObject();           
            switch(nodeObject.Type){
                case Queues:{
                    CurrentUpdateTask = displayQueueList(node, loadNewData);
                    break;
                }
                case Channel:{
                    CurrentUpdateTask = displayChannelList(node, loadNewData);
                    break;
                }
                case ChannelAuth:{
                    CurrentUpdateTask = displayChannelAuthList(node, loadNewData);
                    break;
                }
                default:{
                    resetTimmer();
                    TableHelper.ToggleContentTable(ContentTable, false);                   
                    showContentTableLoading(false);
                    break;
                }
            }
        }        
    }
    
    private StopableTask displayQueueList(DefaultMutableTreeNode node, boolean loadNewData){
        final boolean isNewData = loadNewData;
        resetTimmer();
        UpdateQueueListTask task = new UpdateQueueListTask(this,ContentTable, node, this.activedSearchString, ShowTempObjectToggle.isSelected(), ShowSystemObjectToggleButton.isSelected(),loadNewData);
        showContentTableLoading(true);
        this.ShowTempObjectToggle.setVisible(true);
        task.AddTaskActionSuccessListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNewData){
                     UpdateTimeStampLabel.setText("Last updated : " + DateTimeHelper.GetCurrentTimeStamp());
                }
                showContentTableLoading(false);
            }
        });
        task.AddTaskActionFailListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showContentTableLoading(false);
            }
        });
        Thread thread = new Thread(task);
        thread.start();   
//                    timer.scheduleAtFixedRate(new TimerTask() {
//                        @Override
//                        public void run() {
//                            Thread thread = new Thread(currentUpdateTask);
//                            thread.start();
//                        }
//                    }, 0, 10*1000);    
        return task;
    }
    
    private StopableTask displayChannelList(DefaultMutableTreeNode node,boolean loadNewData){
        final boolean isNewData = loadNewData;
        resetTimmer();
        UpdateChannelListTask task = new UpdateChannelListTask(this, TreeView, ContentTable, node, this.activedSearchString, ShowSystemObjectToggleButton.isSelected(),loadNewData);        
        showContentTableLoading(true);
        this.ShowTempObjectToggle.setVisible(false);
        task.AddTaskActionSuccessListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNewData){
                     UpdateTimeStampLabel.setText("Last updated : " + DateTimeHelper.GetCurrentTimeStamp());
                }
                showContentTableLoading(false);
            }
        });
        task.AddTaskActionFailListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showContentTableLoading(false);
            }
        });
        Thread thread = new Thread(task);
        thread.start();     
//                    timer.scheduleAtFixedRate(new TimerTask() {
//                        @Override
//                        public void run() {
//                            Thread thread = new Thread(currentUpdateTask);
//                            thread.start();
//                        }
//                    }, 0, 10*1000);   
        return task;
    }
    
    private StopableTask displayChannelAuthList(DefaultMutableTreeNode node,boolean loadNewData){
        final boolean isNewData = loadNewData;
        resetTimmer();
        UpdateChannelAuthListTask task = new UpdateChannelAuthListTask(this, TreeView, ContentTable, node, this.activedSearchString, ShowSystemObjectToggleButton.isSelected(),loadNewData);        
        showContentTableLoading(true);
        this.ShowTempObjectToggle.setVisible(false);
        task.AddTaskActionSuccessListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNewData){
                     UpdateTimeStampLabel.setText("Last updated : " + DateTimeHelper.GetCurrentTimeStamp());
                }
                showContentTableLoading(false);
            }
        });
        task.AddTaskActionFailListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showContentTableLoading(false);
            }
        });
        Thread thread = new Thread(task);
        thread.start();     
//                    timer.scheduleAtFixedRate(new TimerTask() {
//                        @Override
//                        public void run() {
//                            Thread thread = new Thread(currentUpdateTask);
//                            thread.start();
//                        }
//                    }, 0, 10*1000);   
        return task;
    }
    
    private void exportProperties(MQObjectType type){
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("csv files","csv");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            String fileName = file.getPath();
            MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
            ArrayList<Object> result = null;
            switch(type){
                case Queue:
                    result = (ArrayList<Object>)(Object)MQPCF.GetQueueList(queueManager, "*", null, true).GetFilterDataModels(this.activedSearchString, ShowTempObjectToggle.isSelected(), ShowSystemObjectToggleButton.isSelected());
                    break;
                case Channel:
                    result = (ArrayList<Object>)(Object)MQPCF.GetChannelList(queueManager, "*", null, true).GetFilterDataModels(this.activedSearchString, ShowSystemObjectToggleButton.isSelected());
                    break;
                case ChannelStatus:
                    result = (ArrayList<Object>)(Object)MQPCF.GetChannelStatusList(queueManager, "*", null, false).DataModels;
                    break;
            }            
            if(result != null && result.size() > 0){                
                try{
                    TextWriter.WriteModelToCSV(result, fileName);
                    JOptionPane.showMessageDialog(this, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception ex){
                    LogWriter.WriteToLog("MainWindow", "exportProperties", ex);
                    JOptionPane.showMessageDialog(this, "Error on saving file.", "File Error", JOptionPane.ERROR_MESSAGE);
                }
   
            }
            else{
                 JOptionPane.showMessageDialog(this, "The list is empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void showSetupQueuePropertiesDialog(){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(ContentTable); 
        QueueProperitiesDialog dialog = DialogFactory.CreateQueueSetupProperitiesDialog(this,true,queueManager,selectedObject.ObjectName);
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);  
    }
    
    private void showSetupChannelPropertiesDialog(){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(ContentTable); 
        ChannelProperitiesDialog dialog = DialogFactory.CreateChannelSetupProperitiesDialog(this,true,queueManager,selectedObject.ObjectName);
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);  

    }
    
    private void createQueue(QueueType type){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        QueueProperitiesDialog dialog = DialogFactory.CreateQueueSetupProperitiesDialog(this, true, queueManager, type);
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);        
    }  
    
    private void createChannel(ChannelType type){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        ChannelProperitiesDialog dialog = DialogFactory.CreateChannelSetupProperitiesDialog(this, true, queueManager, type);
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);        
    }  
    
    private void startChannel(){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(ContentTable);
        MQCommandResult result = MQPCF.StartChannel(queueManager, selectedObject.ObjectName);
        if(result.QuerySuccess){
            JOptionPane.showMessageDialog(this, "Start command accepted", "Success", JOptionPane.INFORMATION_MESSAGE);
            showTableData(true);
        }
        else{
            JOptionPane.showMessageDialog(this, result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void showStopChannelDialog(){
        StopChannelDialog dialog = DialogFactory.CreateOperationalDialog(StopChannelDialog.class, this, true ,TreeView, ContentTable);
        dialog.AddDialogActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);        
    }
    
    private void showResetChannelDialog(){
        ResetChannelDialog dialog = DialogFactory.CreateOperationalDialog(ResetChannelDialog.class, this, true ,TreeView, ContentTable);
        dialog.AddDialogActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);      
    }
    
    private void showResolveChannelDialog(){
        ResolveChannelDialog dialog = DialogFactory.CreateOperationalDialog(ResolveChannelDialog.class, this, true ,TreeView, ContentTable);
        dialog.AddDialogActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData(true);
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);      
    }
    
    private void showStatusDialog(StatusType type){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(ContentTable);   
        StatusDialog dialog = DialogFactory.CreateStatusDialog(this,true,queueManager,selectedObject.ObjectName, type);
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true); 
    }
    
    private void deleteQueue(){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(ContentTable); 
        int dialogResult = JOptionPane.showConfirmDialog (this, "Are you sure that you want to delete queue " + selectedObject.ObjectName +"?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            MQCommandResult result = MQPCF.DeleteQueue(queueManager, selectedObject.ObjectName, (QueueType)selectedObject.Type);
            if(result.QuerySuccess){
                JOptionPane.showMessageDialog(this,"Queue has been deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                showTableData(true);
            }
            else{
                JOptionPane.showMessageDialog(this,result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteChannel(){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(TreeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(ContentTable); 
        int dialogResult = JOptionPane.showConfirmDialog (this, "Are you sure that you want to delete queue " + selectedObject.ObjectName +"?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            MQCommandResult result = MQPCF.DeleteChannel(queueManager, selectedObject.ObjectName);
            if(result.QuerySuccess){
                JOptionPane.showMessageDialog(this,"Channel has been deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                showTableData(true);
            }
            else{
                JOptionPane.showMessageDialog(this,result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void treeSelectionChangeActionPerform(TreeSelectionEvent e){

        this.SearchTextField.setText("");
        this.activedSearchString = "";
        if(CurrentUpdateTask != null){
            CurrentUpdateTask.StopTask();

        }     
        showTableData(true);
    }    
    
    private void showContentTableLoading(boolean isLoading){
        this.ContentTableProgressBar.setVisible(isLoading);    
        if(TreeHelper.ShowSearchBarForCurrentNote(TreeView) && !isLoading){
            this.RefreshButton.setEnabled(true);
            this.ShowSystemObjectToggleButton.setEnabled(true);
            this.ShowTempObjectToggle.setEnabled(true);
            this.SearchButton.setEnabled(true);
            this.SearchTextField.setEnabled(true);
        }
        else{
            this.RefreshButton.setEnabled(false);
            this.ShowSystemObjectToggleButton.setEnabled(false);
            this.ShowTempObjectToggle.setEnabled(false);
            this.SearchButton.setEnabled(false);
            this.SearchTextField.setEnabled(false);
            this.UpdateTimeStampLabel.setText("");
        }
    }
    
    
 //Event handlers   
    private void ChannelStatusToolMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChannelStatusToolMenuItemActionPerformed
        if(!chToolRef.IsOpen){
            ChannelStatusTool chTool = new ChannelStatusTool(chToolRef);
            chTool.start();
        }
    }//GEN-LAST:event_ChannelStatusToolMenuItemActionPerformed

    private void TreeViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeViewMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON3){
            TreePath pathForLocation = TreeView.getPathForLocation(evt.getPoint().x, evt.getPoint().y);
            if(pathForLocation != null){
                TreeView.setSelectionPath(pathForLocation);
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) pathForLocation.getLastPathComponent();
                TreeNodeObject node = (TreeNodeObject)selectedNode.getUserObject();
                switch(node.Type){
                    case QueueManagerFolder:
                        JPopupMenu popupManagerFolder = getTreeViewQueueManagerFolderPopupMenu();
                        popupManagerFolder.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);
                        break;
                    case QueueManager:
                        JPopupMenu popupManager = getTreeViewQueueManagerPopupMenu(node.IsConnected());
                        popupManager.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);
                        break;
                    case Queues:
                        JPopupMenu popupQueue = getTreeViewQueuePopupMenu();
                        popupQueue.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);
                        break;
                    case Channel:
                        JPopupMenu channelQueue = getTreeViewChannelPopupMenu();
                        channelQueue.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);
                        break;                        
                    default:
                        break;
                }              
            }
        }
    }//GEN-LAST:event_TreeViewMouseClicked
       
    private void CancelBittonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBittonActionPerformed
        ConnectionDetailDialog.setVisible(false);
    }//GEN-LAST:event_CancelBittonActionPerformed

    private void AddtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddtButtonActionPerformed
        if(!queueManagerEditPanelEditMode){
            ConnectionDetailModel connectionDetail = GetConnectionDetailModel();
            TreeHelper.AddQueueManagerNodeToTreeView(TreeView, connectionDetail);
            ConnectionDetailModels.add(connectionDetail);
        }
        else{
            ConnectionDetailModel model = TreeHelper.GetCurrentSelectedConnectionDetail(TreeView);
            model.Channel = this.ChannelTextBox.getText();
            model.Host = this.HostTextBox.getText();
            model.Port = this.PortTextBox.getText();
            model.QueueManager = this.QueueManagerTextBox.getText();
            TreeHelper.RefreshTreeView(TreeView, model);
        }
        XMLHelper.WriteConnectionModelToXml(ConnectionDetailModels, SettingFilePath);
        ConnectionDetailDialog.setVisible(false);  
        
    }//GEN-LAST:event_AddtButtonActionPerformed

    private void ContentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContentTableMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON3){
            TableHelper.SetSelectedRow(ContentTable, evt.getPoint());
            TableListObject objectModel = TableHelper.GetCurrentTableSelectRowObject(ContentTable);
            if(objectModel != null){
                switch(objectModel.TableType){
                    case QueueList :
                        JPopupMenu queuePopup = getQueueListTablePopupMenu(objectModel.Type);
                        queuePopup.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);  
                        break;
                    case ChannelList :
                        JPopupMenu channelPopup = getChannelListTablePopupMenu(objectModel.Type);
                        channelPopup.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);  
                        break;
                    default:
                       break;                       
                }
            }
        }      
    }//GEN-LAST:event_ContentTableMouseClicked

    private void TestMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestMenuItemActionPerformed
        ExcelHelper.ReadExcelFile("test.xls");
//        ContentTable = new javax.swing.JTable(){
//            public Class getColumnClass(int column)
//            {
//                int rowCount = getRowCount();
//                Object value = null;
//                value = getValueAt(0, column);
//                if(value == null){
//                    value = getValueAt(rowCount - 1, column);
//                }
//                if(value != null){
//                    try{
//                        Double intValue = Double.parseDouble(value.toString());
//                        return Double.class;
//                    }catch(Exception ex){
//                        return value.getClass();
//                    }
//                }
//                else{
//                    return Object.class;
//                }
//            }
//        };

//                if(getValueAt(0, column) != null)
//                return getValueAt(0, column).getClass();
//                else
//                return Object.class;
        
    }//GEN-LAST:event_TestMenuItemActionPerformed

    private void RefreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RefreshButtonMouseClicked
       showTableData(true);
    }//GEN-LAST:event_RefreshButtonMouseClicked

    private void SearchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchButtonMouseClicked
        this.activedSearchString = this.SearchTextField.getText();
        showTableData(false);
    }//GEN-LAST:event_SearchButtonMouseClicked

    private void ShowSystemObjectToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShowSystemObjectToggleButtonItemStateChanged
       showTableData(false);
    }//GEN-LAST:event_ShowSystemObjectToggleButtonItemStateChanged

    private void ShowTempObjectToggleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShowTempObjectToggleItemStateChanged
       showTableData(false);
    }//GEN-LAST:event_ShowTempObjectToggleItemStateChanged

    private void TreeViewValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_TreeViewValueChanged
        treeSelectionChangeActionPerform(evt);
    }//GEN-LAST:event_TreeViewValueChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        LogWriter.Close();
    }//GEN-LAST:event_formWindowClosing
    
    private void addQueueManageMenuItemActionPerformed(java.awt.event.ActionEvent evt){
        this.ConnectionDetailDialog.setLocationRelativeTo(this);
        ConnectionDetailDialog.setVisible(true);
    }
           
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
//            UIManager.LookAndFeelInfo[] infos = javax.swing.UIManager.getInstalledLookAndFeels();
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    UIManager.setLookAndFeel(
//                                UIManager.getSystemLookAndFeelClassName());
                    break;
                }
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
////                    UIManager.setLookAndFeel(
////                                UIManager.getSystemLookAndFeelClassName());
//                    break;
//                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        ExceptionHandler.registerExceptionHandler();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutMenuItem;
    private javax.swing.JButton AddtButton;
    private javax.swing.JButton CancelBitton;
    private javax.swing.JMenuItem ChannelStatusToolMenuItem;
    private javax.swing.JTextField ChannelTextBox;
    private javax.swing.JDialog ConnectionDetailDialog;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JTable ContentTable;
    private javax.swing.JProgressBar ContentTableProgressBar;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JPanel FilterPanel;
    private javax.swing.JToolBar FilterToolbar;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JTextField HostTextBox;
    private javax.swing.JMenuBar MainMenuBar;
    private javax.swing.JMenu MainWindowMenu;
    private javax.swing.JTextField PortTextBox;
    private javax.swing.JTextField QueueManagerTextBox;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JToggleButton ShowSystemObjectToggleButton;
    private javax.swing.JToggleButton ShowTempObjectToggle;
    private javax.swing.JSplitPane SplitPane;
    private javax.swing.JMenuItem TestMenuItem;
    private javax.swing.JTree TreeView;
    private javax.swing.JPanel TreeViewPanel;
    private javax.swing.JLabel UpdateTimeStampLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
