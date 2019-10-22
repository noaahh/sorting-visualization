package model;

public interface AlgorithmListener {

	void started();
	
	void swaped(int indexA, int indexB);
	
	void finished();
	
}
