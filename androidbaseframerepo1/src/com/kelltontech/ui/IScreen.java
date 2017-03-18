/*
 *
 *  Proprietary and confidential. Property of Kellton Tech Solutions Ltd. Do not disclose or distribute.
 *  You must have written permission from Kellton Tech Solutions Ltd. to use this code.
 *
 */

package com.kelltontech.ui;


/**
 * @author sachin.gupta
 */
public interface IScreen {

    void getData(final int actionID);
    /**
     * Subclass should over-ride this method to update the UI with response. <br/>
     * Subclass should note that it might being called from non-UI thread.
     *
     * @param serviceResponse
     */

    void updateUi(final boolean status, final int actionID, final Object serviceResponse);




}
