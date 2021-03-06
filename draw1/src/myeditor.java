import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxKeyboardHandler;
//import com.mxgraph.util.mxEvent;
//import com.mxgraph.util.mxEventObject;
//import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
//import com.mxgraph.util.mxDomUtils;

/*class PopUpDemo extends JPopupMenu {
	
	ActionListener test = new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        System.out.println("Popup menu item ["
	            + event.getActionCommand() + "] was pressed.");
	      }
	    };
	void test() {
		System.out.println("LOG: test");
	}
	private static final long serialVersionUID = 1L;
	JMenuItem anItem;
    //@SuppressWarnings("deprecation")
	public PopUpDemo(){
        
    	anItem = new JMenuItem("test1");
    	anItem.setActionCommand(test);
    	//anItem.setLabel("bla");
        add(anItem);

    }
}*/

public class myeditor extends JFrame
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2764911804288120883L;

	public myeditor()
	{
		super("Ontology Editor (pre-alpha) | Bauer (R) 2013");
		
		
		
        //getContentPane().setBackground(Color.WHITE);
		
		final mxGraph graph = new mxGraph();
		
		final Object parent = graph.getDefaultParent();
		
		//Container c = null;// = getContentPane();
        //c.setBackground(Color.WHITE);
        
		//c.getContentPane().setBackground( Color.WHITE );
		
		graph.getModel().beginUpdate();
		try
		{
		   //Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
		   //Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
		   //graph.insertEdge(parent, null, "Edge", v1, v2);
			//graph.insertVertex(parent, null, "", 20, 20, 80, 30);
		}
		finally
		{
		   graph.getModel().endUpdate();
		}
		
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		
		new mxKeyboardHandler(graphComponent);//������ �������� delete
		

		
		
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
		
			public void mouseReleased(MouseEvent e) {
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());
				if (cell != null) {
					System.out.println("cell="+graph.getLabel(cell));
					
					if (e.getButton() == 3) {//������ ������)
				        //System.out.println(" right click" );
				        
				        
				        
				        //PopUpDemo menu = new PopUpDemo();//popup menu
				        //menu.show(e.getComponent(), e.getX(), e.getY());
						
						JPopupMenu popup = new JPopupMenu();
						
					    ActionListener menuListener = new ActionListener() {
					      public void actionPerformed(ActionEvent event) {
					        System.out.println("Popup menu item [" + event.getActionCommand() + "] was pressed.");
					      }
					    };
					    
					    JMenuItem item;
					    popup.add(item = new JMenuItem("test"));
					    //item.setHorizontalTextPosition(JMenuItem.RIGHT);
					    item.addActionListener(menuListener);
					    popup.add(item = new JMenuItem("cancel"));
					    //item.setHorizontalTextPosition(JMenuItem.RIGHT);
					    item.addActionListener(menuListener);
					    
					    popup.show(e.getComponent(), e.getX(), e.getY());
					    //popup.add(item = new JMenuItem("Right", new ImageIcon("3.gif")));
					    //item.setHorizontalTextPosition(JMenuItem.RIGHT);
					    //item.addActionListener(menuListener);
					    //popup.add(item = new JMenuItem("Full", new ImageIcon("4.gif")));
					    //item.setHorizontalTextPosition(JMenuItem.RIGHT);
					    //item.addActionListener(menuListener);
					    //popup.addSeparator();
					    //popup.add(item = new JMenuItem("Settings . . ."));
					    //item.addActionListener(menuListener);
				        
				        
						
				        //graph.insertVertex(parent, null, "", e.getX(), e.getY(), 80, 30);
				    }
					
				} else {
					if (e.getClickCount() == 2) {//������� ����)
				        //System.out.println(" double click" );
						graph.insertVertex(parent, null, "", e.getX(), e.getY(), 80, 30);
				    }
					
				}
				
			}
			
			//public void mouseDoubleClick(MouseEvent e)
			//{
			//	System.out.println("Mouse Double click.");
			//}
		});
		
	}
	
/*	public void actionPerformed(ActionEvent ae) {
		//DefaultMutableTreeNode dmtn, node;

		   //TreePath path = this.getSelectionPath();
		   //dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
		   
		if (ae.getActionCommand().equals("test")) {
			
			System.out.println("test");
			
            //node = (DefaultMutableTreeNode)dmtn.getParent();
            //node.removeAllChildren();
            //((DefaultTreeModel )this.getModel())
            //     .nodeStructureChanged((TreeNode)dmtn); 
            //}
          }
	}
*/	
	public static void main(String[] args)
	{
		myeditor frame = new myeditor();
		//frame.getContentPane().setBackground( Color.WHITE );
		//Color c = new Color(0,255,0);
		//frame.setBackground(c);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		
		frame.setVisible(true);
		
	}

}
