
import javax.swing.*;

import java.awt.*;
import java.util.Random;

class Visualizer extends JPanel {

	int x = 0;
	long animDuration = 50000000;
    long currentTime = System.nanoTime() / 1000000;
    long startTime = currentTime;
    long elapsedTime = currentTime - startTime;
    
    static int WIDTH = 1920;
    static int HEIGHT = 1080;
    static int CITY_RADIUS = 10;
    static double SCALE = 0.7;
    
    int padding = 10;
    int minx = 10000000;
    int maxx = 0;
    int miny = 10000000;
    int maxy = 0;
    
    
    Node[] cities = null;
    Node[] path = null;

    public Visualizer(Path path) {
    	
    	Node[] cities = path.nodes.clone();
    	int x, y;
    	for (int i = 0; i < cities.length; i++) {
    		x = (int)cities[i].getX();
    		y = (int)cities[i].getY();
			if(x>maxx){
				maxx = x;
			}
			if(y>maxy){
				maxy = y;
			}
			if(x<minx){
				minx = x;
			}
			if(y<miny){
				miny = y;
			}
		}
    	maxx -= minx-(2*padding);
    	maxy -= miny-(2*padding);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.cities = cities;
        //i removed the call to runAnimation from here
    }

    public void runAnimation() {
        while (elapsedTime < animDuration) {
            currentTime = System.nanoTime() / 1000000;
            elapsedTime = currentTime - startTime;
            //System.out.println(elapsedTime);
            x++;
            x = x%10;
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
            }
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
    	
    	Rectangle clip = g.getClipBounds();
        g.setColor(Color.BLACK);
        g.fillRect(clip.x, clip.y, clip.width, clip.height);
        g.setColor(Color.BLUE);
        
        if(cities!=null){
	    	synchronized (cities) {
	    		
	    		
	    		if(this.path != null){
					for (int i = 0; i < path.length; i++) {
						drawpath(g, i);
					}
	    		}
				if(cities != null){
					for (int i = 0; i < cities.length; i++) {
						drawcity(g, i);
					}
				}
			}
    	}

    }

    public void drawpath(Graphics g, int i){
    	Node from = path[i];
    	Node to = path[(i+1)%path.length];
    	
    	double x1quota = ((double)(
				(int)from.getX()-minx
		))
		/(double)maxx;
    	double y1quota = ((double)(
				(int)from.getY()-miny
		))
		/(double)maxy;
    	double x2quota = ((double)(
				(int)to.getX()-minx
		))
		/(double)maxx;
    	double y2quota = ((double)(
				(int)to.getY()-miny
		))
		/(double)maxy;
    	
    	int x1 = (int)((x1quota)*WIDTH)+padding;
    	int y1 = (int)((y1quota)*HEIGHT)+padding;
    	int x2 = (int)((x2quota)*WIDTH)+padding;
    	int y2 = (int)((y2quota)*HEIGHT)+padding;
    	
    	g.drawLine(
    			(int)(x1*SCALE), 
    			(int)(y1*SCALE), 
    			(int)(x2*SCALE), 
    			(int)(y2*SCALE));
        
    }
    
    public void drawcity(Graphics g, int i){
    	Node city = cities[i];
    	double xquota = ((double)(
				(int)city.getX()-minx
		))
		/(double)maxx;
    	double yquota = ((double)(
				(int)city.getY()-miny
		))
		/(double)maxy;
    	
    	
    	
    	int x1 = (int)((xquota)*WIDTH)+padding;
    	int y1 = (int)((yquota)*HEIGHT)+padding;
    	
    	g.fillOval(
    			(int)(SCALE*x1)-(CITY_RADIUS/2), 
    			(int)(SCALE*y1)-(CITY_RADIUS/2), 
    			CITY_RADIUS, 
    			CITY_RADIUS);
    }
    
    public void updatePath(Path newPath){
    	synchronized (cities) {
    		this.path = newPath.getNodes().clone();
    	}
    }
    
//    public static void main(String[] args) {
//        Visualizer visual = createAndShowGUI(cities);
//        visual.updatePath(newPath);
//    }

    public static Visualizer createAndShowGUI(Path cities) {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Visualizer animationPanel = new Visualizer(cities);
        mainFrame.add(animationPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        //I made the call to runAnimation here now
        //after the containing frame is visible.
        new Thread()
        {
            public void run() {
            	animationPanel.runAnimation();
            }
        }.start();
        
        //System.out.print(animationPanel);
        return animationPanel;
    }
}
