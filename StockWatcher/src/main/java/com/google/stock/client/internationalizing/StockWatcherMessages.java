package com.google.stock.client.internationalizing;

import com.google.gwt.i18n.client.Messages;

import java.util.Date;

//@Constants.GeneratedFrom("/StockWatcherMessages_vi.properties")
public interface StockWatcherMessages extends Messages {

    @Messages.DefaultMessage("''{0}'' is not a valid symbol.")
    String invalidSymbol(String symbol);

    @Messages.DefaultMessage("''{0}'' is already exist")
    String existSymbol(String symbol);

    @Messages.DefaultMessage("Last update: {0, date, medium}")
    String lastUpdate(Date timestamp);
}
