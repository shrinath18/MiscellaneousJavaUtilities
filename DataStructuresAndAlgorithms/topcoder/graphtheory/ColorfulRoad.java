package graphtheory;

import java.util.ArrayList;
import java.util.List;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

/*
 * There is a one-dimensional road. The road is separated into N consecutive parts. The parts are numbered 0 through N-1, in order. Ciel is going to walk from part 0 to part N-1.

Ciel also noticed that each part of the road has a color: either red, green, or blue. Part 0 is red.

Ciel is going to perform a sequence of steps. Each step must lead in the positive direction. That is, if her current part is i, the next step will take her to one of the parts i+1 through N-1, inclusive. Her steps can be arbitrarily long. However, longer steps are harder: a step of length j costs j*j energy.

Additionally, Ciel wants to step on colors in a specific order: red, green, blue, red, green, blue, ... That is, she starts on the red part 0, makes a step to a green part, from there to a blue part, and so on, always repeating red, green, and blue in a cycle. Note that the final part N-1 also has some color and thus Ciel must reach it in a corresponding step.

You are given a String road containing N elements. For each i, element i of road is the color of part i: 'R' represents red, 'G' green, and 'B' blue. If Ciel can reach part N-1 in the way described above, return the smallest possible total cost of doing so. Otherwise, return -1.*/
public class ColorfulRoad {
	List<Integer> energies = new ArrayList<Integer>();
	public void getMin(String road){
		char roadStep[] = road.toCharArray();
		System.out.print("road step:"+roadStep.length);
		energies.add(-1);
		calculateEnergy(roadStep, 0, 0);
		System.out.print("Minimum Energy:"+getMinimumEnergy());
		
	}
	public void calculateEnergy(char[] roadStep,int energySpent,int curPosition){
		if(curPosition == roadStep.length-1){
			//We have reached the end of road,add to the energy list
			energies.add(energySpent);
			return;
		}
		//Find out the next possible steps
		
		for(int i= curPosition+1;i<roadStep.length;i++){
			if(roadStep[curPosition]=='R'){
				if(roadStep[i] != 'G')
					continue;
			}else if(roadStep[curPosition] == 'G'){
				if(roadStep[i] != 'B')
					continue;
			}else if(roadStep[curPosition] == 'B'){
				if(roadStep[i] != 'R')
					continue;
			}
		    int stepLength = i-curPosition;
		    calculateEnergy(roadStep, energySpent+stepLength*stepLength, i);
		}
		
	}
	public int getMinimumEnergy(){
		if(energies.size() == 1){
			return -1;
		}
		int min = energies.get(1);
		for (int i = 1; i < energies.size(); i++) {
			if(energies.get(i)<min)
				min = energies.get(i);
		}
		return min;
	}

	public static void main(String [] args){
		ColorfulRoad colorfulRoad = new ColorfulRoad();
		colorfulRoad.getMin("RGGGB");
	}
}
