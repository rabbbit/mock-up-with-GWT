/*
 * Ext GWT 2.2.1 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package me.smape.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.event.ChartEvent;
import com.extjs.gxt.charts.client.event.ChartListener;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;
import com.extjs.gxt.charts.client.model.Legend.Position;
import com.extjs.gxt.charts.client.model.charts.PieChart;
import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.desktop.client.StartMenu;
import com.extjs.gxt.desktop.client.TaskBar;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Frame;

public class Smape implements EntryPoint {

	private Desktop desktop = new Desktop();
	

	private void itemSelected(ComponentEvent ce) {
		Window w;
		if (ce instanceof MenuEvent) {
			MenuEvent me = (MenuEvent) ce;
			w = me.getItem().getData("window");
		} else {
			w = ce.getComponent().getData("window");
		}
		if (!desktop.getWindows().contains(w)) {
			desktop.addWindow(w);
		}
		if (w != null && !w.isVisible()) {
			w.show();
		} else {
			w.toFront();
		}
	}
	
	private Window createGeolocationWindow() { 
		
		final Window w = new Window();
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setIcon(IconHelper.createStyle("accordion"));
		w.setHeading("Contact list");
		w.setWidth(500);
		w.setHeight(500);
		w.setLayout(new FitLayout());
		
		ContentPanel cp = new ContentPanel();
		cp.setAnimCollapse(false);
		cp.setHeading("Localize your friends");
		cp.setScrollMode(Scroll.NONE);
		cp.setLayout(new FitLayout());  

		Frame frame = new Frame();
		frame.setUrl("geolocation.html");
		frame.setLayoutData(new FitLayout());
		cp.add(frame);
		
		w.add(cp);
		return w; 
	}
	
    public Window getEmptyWindow() {
    	
    	Window w = new Window();
		w.setMinimizable(false);
		w.setMaximizable(false);
		w.setBodyBorder(false);
		w.setBorders(false);
		w.setClosable(false);
		w.setHeaderVisible(false);
    	return w; 
    }
    
    public Window createPrezioWindow() {
    	
    	Window w = new Window();
    	w.setSize(500, 500);
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setBodyBorder(false);
		w.setBorders(false);
		w.setHeading("Presentation:)");
		
		Frame frame = new Frame();
		frame.setUrl("https://prezi.com/secure/40398e8bef489ebec26c2f09c4e0cf4b1d419524/");
		frame.setLayoutData(new FitLayout());
		w.setLayout(new FitLayout());
		w.add(frame);
		
    	return w; 
    }
    
	private Window createStatisticWindow() { 

		Window w = new Window();
		w.setIcon(IconHelper.createStyle("bogus"));
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setHeading("Statistics ");
		w.setSize(400, 400);
		
		w.setLayout(new AccordionLayout());

		ContentPanel cp = new ContentPanel();  
		cp.setHeading("Most contacted friends this month");  
		cp.setFrame(false);  
		cp.setSize(300, 300);  
		
		Frame frame = new Frame();
		frame.setUrl("chart.html");
		frame.setLayoutData(new FitLayout());
		cp.setLayout(new FitLayout());
		cp.add(frame);

		w.add(cp);
		
		cp = new ContentPanel();  
		cp.setHeading("Most contacted friends in total");  
		cp.setFrame(false);  
		cp.setSize(300, 300);  
		
		frame = new Frame();
		frame.setUrl("https://chart.googleapis.com/chart?cht=p3&chs=350x200&chd=t:70,30,10&&chl=Hollie|Emerson|Christinal");
		frame.setLayoutData(new FitLayout());
		cp.setLayout(new FitLayout());
		cp.add(frame);
		
		w.add(cp);
		
		cp = new ContentPanel();  
		cp.setHeading("Average sms per day last two weeks");  
		cp.setFrame(false);  
		cp.setSize(300, 300);  
		
		frame = new Frame();
		frame.setUrl("https://chart.googleapis.com/chart?cht=lc&chs=350x200&chd=t:25,50,70,90,25,44,56,34,23,56,0,0,34,5,5,103,3,2,6,13&&chl=1|2|3|4|5|6|7|8|9|10|11|12|13|14&chxt=x,y");
		frame.setLayoutData(new FitLayout());
		cp.setLayout(new FitLayout());
		cp.add(frame);  

		w.add(cp); 

		return w;
	}
    
    public Window createVideoWindow() {
    	
    	Window w = new Window();
    	w.setSize(800, 800);
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setBodyBorder(false);
		w.setBorders(false);
		w.setHeading("Received mms:)");
		
		Frame frame = new Frame();
		frame.setUrl("video.html");
		frame.setLayoutData(new FitLayout());
		w.setLayout(new FitLayout());
		w.add(frame);
		
    	return w; 
    }

	public void onModuleLoad() {
		GXT.setDefaultTheme(Theme.GRAY, true);
		
		Window gridWindow = createGridWindow();
		Window accordionWindow = createAccordionWindow();
		Window statisticWindow = createStatisticWindow();
		Window geolocationWindow = createGeolocationWindow(); 
		Window prezioWindow = createPrezioWindow(); 
		Window videoWindow = createVideoWindow(); 
		
//		Dispatcher dispatcher = Dispatcher.get();
//	    dispatcher.dispatch(AppEvents.Login);
	    
	    

//	    desktop.getDesktop().hide();
	    
//	    desktop.getShortcuts().noti

//	    desktop.getDesktop().setEnabled(false);
//	    desktop.getDesktop().hide();
//	    GXT.hideLoadingPanel("loading");
//		desktop.getDesktop().setEnabled(true);
//		desktop.getDesktop().show();
//		desktop.getDesktop().setZIndex(10);
//	    desktop.getTaskBar().setVisible(false);
	    Window w = getEmptyWindow();
	    desktop.addWindow(w);
	    w.show();
	    w.maximize();
	    LoginDialog login = new LoginDialog(w);
	    
	    w.setZIndex(1);
	    login.show();
	    login.focus();
	    login.setVisible(true);
	    
  
	
		desktop.addWindow(prezioWindow);
		prezioWindow.show();
		prezioWindow.maximize();
		prezioWindow.focus();
		
//	    login.setZIndex(15);
	    
		SelectionListener<MenuEvent> menuListener = new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent me) {
				itemSelected(me);
			}
		};

		SelectionListener<ComponentEvent> shortcutListener = new SelectionListener<ComponentEvent>() {
			@Override
			public void componentSelected(ComponentEvent ce) {
				itemSelected(ce);
			}
		};


		

		Shortcut s1 = new Shortcut();
		s1.setText("Messages Window");
		s1.setId("grid-win-shortcut");
		s1.setData("window", gridWindow);
		s1.addSelectionListener(shortcutListener);
		desktop.addShortcut(s1);


		Shortcut s2 = new Shortcut();
		s2.setText("Contact list");
		s2.setId("acc-win-shortcut");
		s2.setData("window", accordionWindow);
		s2.addSelectionListener(shortcutListener);
		desktop.addShortcut(s2);

		Shortcut s3 = new Shortcut();
		s3.setText("Statistics");
		s3.setId("stat-win-shortcut");
		s3.setData("window", statisticWindow);
		s3.addSelectionListener(shortcutListener);
		desktop.addShortcut(s3);

		TaskBar taskBar = desktop.getTaskBar();

		StartMenu menu = taskBar.getStartMenu();
		menu.setHeading("Eurecom Presentation!");
		menu.setIconStyle("user");

		MenuItem menuItem = new MenuItem("Messages Window");
		menuItem.setData("window", gridWindow);
		menuItem.setIcon(IconHelper.createStyle("icon-grid"));
		menuItem.addSelectionListener(menuListener);
		menu.add(menuItem);

		menuItem = new MenuItem("Archives Window");
		menuItem.setIcon(IconHelper.createStyle("tabs"));
		menuItem.addSelectionListener(menuListener);
		menuItem.setData("window", createTabWindow());
		menu.add(menuItem);

		menuItem = new MenuItem("Contact list");
		menuItem.setIcon(IconHelper.createStyle("accordion"));
		menuItem.addSelectionListener(menuListener);
		menuItem.setData("window", accordionWindow);
		menu.add(menuItem);

		menuItem = new MenuItem("Statistic window");
		menuItem.setIcon(IconHelper.createStyle("icon-statistic"));
		menuItem.addSelectionListener(menuListener);
		menuItem.setData("window", statisticWindow);
		menu.add(menuItem);
		
		menuItem = new MenuItem("Geolocation window");
		menuItem.setIcon(IconHelper.createStyle("icon-geo"));
		menuItem.addSelectionListener(menuListener);
		menuItem.setData("window", geolocationWindow);
		menu.add(menuItem);


		menuItem = new MenuItem("Last messages");
		menuItem.setIcon(IconHelper.createStyle("icon-sms-menu"));


		Menu sub = new Menu();

		for (int i = 0; i < 3; i++) {
			MenuItem item = new MenuItem("Message " + (i + 1));
			item.setData("window", createBogusWindow(i));
			item.setIcon(IconHelper.createStyle("icon-sms"));
			item.addSelectionListener(menuListener);
			sub.add(item);
		}
		
		MenuItem item = new MenuItem("Received mms");
		item.setIcon(IconHelper.createStyle("icon-video"));
		item.addSelectionListener(menuListener);
		item.setData("window", videoWindow);
		sub.add(item);
		
		item = new MenuItem("Presentation window");
		item.setIcon(IconHelper.createStyle("icon-ppt"));
		item.addSelectionListener(menuListener);
		item.setData("window", prezioWindow);
		sub.add(item);

		menuItem.setSubMenu(sub);
		
		menu.add(menuItem);

		// tools
		MenuItem tool = new MenuItem("Settings");
		tool.setIcon(IconHelper.createStyle("settings"));
		tool.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				Info.display("Event", "The 'Settings' tool was clicked");
			}
		});
		menu.addTool(tool);

		menu.addToolSeperator();

		tool = new MenuItem("Logout");
		tool.setIcon(IconHelper.createStyle("logout"));
		tool.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				Info.display("Event", "The 'Logout' tool was clicked");
			}
		});
		menu.addTool(tool);
	}
	
	private Window createAccordionWindow() {
		final Window w = new Window();
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setIcon(IconHelper.createStyle("accordion"));
		w.setHeading("Contact list");
		w.setWidth(200);
		w.setHeight(350);

		ToolBar toolBar = new ToolBar();
		Button item = new Button();
		item.setIcon(IconHelper.createStyle("icon-connect"));
		toolBar.add(item);

		toolBar.add(new SeparatorToolItem());
		w.setTopComponent(toolBar);

		item = new Button();
		item.setIcon(IconHelper.createStyle("icon-user-add"));
		toolBar.add(item);

		item = new Button();
		item.setIcon(IconHelper.createStyle("icon-user-delete"));
		toolBar.add(item);

		w.setLayout(new AccordionLayout());

		ContentPanel cp = new ContentPanel();
		cp.setAnimCollapse(false);
		cp.setHeading("Online Users");
		cp.setScrollMode(Scroll.AUTO);
		cp.getHeader().addTool(new ToolButton("x-tool-refresh"));

		w.add(cp);

		TreeStore<ModelData> store = new TreeStore<ModelData>();
		TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setIconProvider(new ModelIconProvider<ModelData>() {

			public AbstractImagePrototype getIcon(ModelData model) {
				if (model.get("icon") != null) {
					return IconHelper.createStyle((String) model.get("icon"));
				} else {
					return null;
				}
			}

		});
		tree.setDisplayProperty("name");

		ModelData m = newItem("Eurecom", null);
		store.add(m, false);
		tree.setExpanded(m, true);

		store.add(m, newItem("Emerson Milton", "user-suit"), false);
		store.add(m, newItem("Christina Blake", "user-girl"), false);
		store.add(m, newItem("Heriberto Rush", "user-kid"), false);
		store.add(m, newItem("Candice Carson", "user-girl"), false);
		store.add(m, newItem("Bell Snedden", "user-kid"), false);
		store.add(m, newItem("Dirk Newman", "user-kid"), false);
		store.add(m, newItem("Chad Andrews", "user-suit"), false);


		m = newItem("Italy", null);
		store.add(m, false);
		tree.setExpanded(m, true);
		store.add(m, newItem("Benito Meeks", "user-suit"), false);
		store.add(m, newItem("Gail Horton", "user-girl"), false);
		
		m = newItem("Paris", null);
		store.add(m, false);
		tree.setExpanded(m, true);
		store.add(m, newItem("Claudio Engle", "user-suit"), false);
		store.add(m, newItem("Sine", "user-girl"), false);
		
		m = newItem("Hometown", null);
		store.add(m, false);
		tree.setExpanded(m, true);
		store.add(m, newItem("Hollie Voss", "user-girl"), false);

		cp.add(tree);

		cp = new ContentPanel();
		cp.setAnimCollapse(false);
		cp.setHeading("Settings");
		cp.setBodyStyleName("pad-text");
		cp.addText("Smape1");
		w.add(cp);

		cp = new ContentPanel();
		cp.setAnimCollapse(false);
		cp.setHeading("Blocked");
		cp.setBodyStyleName("pad-text");
		cp.addText("Smape2");
		w.add(cp);

		return w;
	}

	private Window createGridWindow() {
		Window w = new Window();
		w.setIcon(IconHelper.createStyle("icon-grid"));
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setHeading("Messages Window");
		w.setSize(500, 400);
		w.setLayout(new FitLayout());

		GroupingStore<Employee> employeeList = new GroupingStore<Employee>();  
		employeeList.add(TestData.getEmployees());  
		employeeList.groupBy("department");   

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();  
		column.setId("name");  
		column.setHeader("Sender Name");  
		column.setWidth(200);  
		configs.add(column);

		column = new ColumnConfig("department", "User group", 150);  
		column.setAlignment(HorizontalAlignment.LEFT);  
		configs.add(column);

		column = new ColumnConfig("designation", "Taggs", 150);   
		column.setAlignment(HorizontalAlignment.LEFT);  
		configs.add(column);

		column = new ColumnConfig("salary", "Length", 100);  
		column.setAlignment(HorizontalAlignment.RIGHT);  
		final NumberFormat number = NumberFormat.getFormat("0.00");  
		GridCellRenderer<Employee> checkSalary = new GridCellRenderer<Employee>() {  
			public String render(Employee model, String property, ColumnData config, int rowIndex,  
					int colIndex, ListStore<Employee> employeeList, Grid<Employee> grid) {  
				double val = (Double) model.get(property);  
				String style = val < 70000 ? "red" : "green";  
				return "<span style='color:" + style + "'>" + number.format(val) + "</span>"; 
			}  
		};  
		column.setRenderer(checkSalary);  
		configs.add(column);

		column = new ColumnConfig("joiningdate", "Date", 100);  
		column.setAlignment(HorizontalAlignment.RIGHT);  
		column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());  
		configs.add(column);

		final ColumnModel cm = new ColumnModel(configs); 
		
		GroupingView view = new GroupingView();  
		view.setForceFit(true);  
		view.setGroupRenderer(new GridGroupRenderer() {  
		public String render(GroupColumnData data) {  
		String f = cm.getColumnById(data.field).getHeader();  
		String l = data.models.size() == 1 ? "Item" : "Items";  
		return f + ": " + data.group + " (" + data.models.size() + " " + l + ")";  
		}  
		});  

		Grid<Employee> grid = new Grid<Employee>(employeeList, cm);  
		grid.setView(view);  
		grid.setBorders(true);
		
		ContentPanel cp = new ContentPanel();  
		cp.setBodyBorder(false);  
		cp.setHeading("Friends List");  
		cp.setButtonAlign(HorizontalAlignment.CENTER);  
		cp.setLayout(new FitLayout());  
		cp.setSize(700, 420); 
		cp.add(grid);  
		w.add(cp);

		return w;
	}

	private ChartListener listener = new ChartListener() {  
	      
	    public void chartClick(ChartEvent ce) {  
	      Info.display("Chart Clicked", "You selected {0}.", "" + ce.getValue());  
	    }  
	};

	private ChartModel getPieChartData() { 

		ChartModel cm = new ChartModel("Most contacted friends",  
		"font-size: 14px; font-family: Verdana; text-align: center;");  
		cm.setBackgroundColour("#fffff5");  
		Legend lg = new Legend(Position.RIGHT, true);  
		lg.setPadding(10);  
		cm.setLegend(lg);  

		PieChart pie = new PieChart();  
		pie.setAlpha(0.5f);  
		pie.setNoLabels(true);  
//		pie.setTooltip("#label# $#val#M<br>#percent#");  
		pie.setColours("#ff0000", "#00aa00", "#0000ff", "#ff9900", "#ff00ff");  
		pie.addSlices(new PieChart.Slice(100, "A","Alice"));  
		pie.addSlices(new PieChart.Slice(200, "H", "John"));  
		pie.addSlices(new PieChart.Slice(150, "B", "Barack"));  
		pie.addSlices(new PieChart.Slice(120, "H", "Hilary"));  
		pie.addSlices(new PieChart.Slice(60, "F", "Franz"));  
		pie.addChartListener(listener);  

		cm.addChartConfig(pie);  
		return cm;  
	}  

	private Window createTabWindow() {
		Window w = new Window();
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setSize(740, 480);
		w.setIcon(IconHelper.createStyle("tabs"));
		w.setHeading("Archives Window");

		w.setLayout(new FitLayout());

		TabPanel panel = new TabPanel();

		for (int i = 0; i < 4; i++) {
			TabItem item = new TabItem("Tab Item " + (i + 1));
			item.addText("Something useful would be here");
			panel.add(item);
		}

		w.add(panel);
		return w;
		//    return w;
	}

	private Window createBogusWindow(int index) {
		Window w = new Window();
		w.setIcon(IconHelper.createStyle("bogus"));
		w.setMinimizable(true);
		w.setMaximizable(true);
		w.setHeading("Last messages window " + ++index);
		w.setSize(400, 300);
		w.addText("Here would be last seen message, \npossibly shown is some fancy way"); 
		return w;
	}

	private ModelData newItem(String text, String iconStyle) {
		ModelData m = new BaseModelData();
		m.set("name", text);
		m.set("icon", iconStyle);
		return m;
	}
}
