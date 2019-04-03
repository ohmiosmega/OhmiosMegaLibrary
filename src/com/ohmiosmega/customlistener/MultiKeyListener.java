package com.ohmiosmega.customlistener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 30/03/2019
 * @version 1.0
 **/
public abstract class MultiKeyListener implements KeyListener {
	protected ArrayList<Integer> keys;

	public MultiKeyListener() {
		this.keys = new ArrayList<>();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.addKey(e.getKeyCode());
		this.keyDirector();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.removeKey(e.getKeyCode());
		this.keyDirector();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	/**
	 * Se activa cada vez que es pulsada y soltada una tecla
	 */
	protected abstract void keyDirector(); 
	/**
	 * Se deshace el valor de una tecla para no tomarlo en cuenta en keyDirector
	 * @param e valor a eliminar
	 */
	protected abstract void removeKey(int e);
	
	/**
	 * Acumula el valor de una tecla para tomarlo en cuenta en keyDirector
	 * @param e valor a agregar
	 */
	protected void addKey(int e) {
		if (!this.keys.contains(e))
			this.keys.add(e);
	}


}
