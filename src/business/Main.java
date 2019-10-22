package business;

import java.awt.EventQueue;

import model.algorithms.BubbleSort;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Controller(new BubbleSort(120));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
