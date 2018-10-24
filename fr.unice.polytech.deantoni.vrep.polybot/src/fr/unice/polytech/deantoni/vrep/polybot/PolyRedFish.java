package fr.unice.polytech.deantoni.vrep.polybot;
import java.util.ArrayList;

import fr.unice.polytech.deantoni.vrep.polybot.robot.PolyRob;
import fr.unice.polytech.deantoni.vrep.polybot.utils.Blob;

public class PolyRedFish {
	
	public static void main(String[] args) {
		PolyRob rob = new PolyRob("127.0.0.1", 19997);
		
		//redFishStuff(rob);
		
		rob.start();
		rob.openGrip();
	
		rob.sleep(800);
		int i = 0;
		while(i < 5) {
			i++;
			 double dist = 10;
			while (!rob.hasDetectedAnObject() || dist > 2) {
				 dist = rob.detectedObjectPoint.getArray()[0]*rob.detectedObjectPoint.getArray()[0]+rob.detectedObjectPoint.getArray()[1]*rob.detectedObjectPoint.getArray()[1]+rob.detectedObjectPoint.getArray()[2]*rob.detectedObjectPoint.getArray()[2];
				ArrayList<Blob> blobs = rob.getViewableBlobs();
				for(Blob b : blobs) {
					System.out.println("\t blob ["+b.positionX+" ; "+b.positionY+"]");
				}
				int[] pos = rob.getPosition();
				System.out.println("rob ["+pos[0]+" ; "+pos[1]+"]");
				int sl = (int) (Math.random()*15);
				int sr = (int) (Math.random()*15);
				rob.goCurved(sl, sr);
				rob.sleep(100);
			}
			
		
			
			
			rob.goStraight(0);
			// Now send some data to V-REP in a non-blocking fashion:
	
	       dist = rob.detectedObjectPoint.getArray()[0]*rob.detectedObjectPoint.getArray()[0]+rob.detectedObjectPoint.getArray()[1]*rob.detectedObjectPoint.getArray()[1]+rob.detectedObjectPoint.getArray()[2]*rob.detectedObjectPoint.getArray()[2];
	       rob.log2vrep("dist "+dist);
	
			while(dist > 0.0045) {
				
				System.out.println(dist+"\n\t"+rob.detectedObjectPoint.getArray()[0]+" "+rob.detectedObjectPoint.getArray()[1]+" "+rob.detectedObjectPoint.getArray()[2]);
				rob.goStraight(2);
				if (rob.detectedObjectPoint.getArray()[0] > 0){
					rob.goCurved(2,3);
					System.out.println("turn left");
				}
				else{
					rob.goCurved(3,2);
					System.out.println("turn right");
				}
				rob.log2vrep("dist "+dist);
				rob.sleep(100);
				rob.hasDetectedAnObject();
				dist = rob.detectedObjectPoint.getArray()[0]*rob.detectedObjectPoint.getArray()[0]+rob.detectedObjectPoint.getArray()[1]*rob.detectedObjectPoint.getArray()[1]+rob.detectedObjectPoint.getArray()[2]*rob.detectedObjectPoint.getArray()[2];
			}
			rob.log2vrep("last dist "+dist);
			
			rob.closeGrip();
			rob.sleep(1500);
			rob.goStraight(-4, 500);
			rob.turnLeft(7, 2000);
			rob.goStraight(15);
			rob.sleep(4000);
			rob.openGrip();
			rob.goStraight(-5, 3000);
			rob.turnLeft(6,1000);
			rob.sleep(1500);
		}
		rob.stopSimulation();
	}

	protected static void redFishStuff(PolyRob rob) {
		int i = 0;
		rob.start();
		while(i++ < 10) {
			if(i%2 == 0) {
				rob.openGrip();
			}else {
				rob.closeGrip();
			}
			rob.goStraight(5);
			while (!rob.hasDetectedAnObject()) {
				rob.sleep(100);
			}
			rob.goStraight(-6);
			rob.sleep(400);
			if(rob.detectedObjectPoint.getArray()[2] > rob.detectedObjectPoint.getArray()[0]) {
				rob.turnRight(6);
				rob.sleep(600);
			}else {
				rob.turnRight(6);
				rob.sleep(600);
			}
		}
		rob.stopSimulation();
	}

}