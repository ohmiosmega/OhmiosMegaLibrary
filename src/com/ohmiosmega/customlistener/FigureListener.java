package com.ohmiosmega.customlistener;

import java.util.EventListener;

import com.ohmiosmega.figure.AbstractFigure;

/**
 * @author Andrés Felipe Chaparro Rosas
 * @date 2/04/2019
 **/
public interface FigureListener extends EventListener {

	public void focus(AbstractFigure figure);

}
