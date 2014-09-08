package com.google.stock.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.stock.client.internationalizing.StockWatcherConstants;
import com.google.stock.client.internationalizing.StockWatcherMessages;
import com.google.stock.client.model.StockPrice;

import java.util.ArrayList;
import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcher implements EntryPoint {

    private static final int REFRESH_INTERVAL = 2000;
    private Button addBStock;
    private TextBox stockNameTxt;
    private FlexTable stocksFlexTable;
    private Label lastUpdatedLabel;
    private ArrayList<String> stocks;
    private StockWatcherConstants constants = GWT.create(StockWatcherConstants.class);
    private StockWatcherMessages messages = GWT.create(StockWatcherMessages.class);

    public StockWatcher() {
        addBStock = new Button("Add");
        stockNameTxt = new TextBox();
        stocksFlexTable = new FlexTable();
        lastUpdatedLabel = new Label();
        stocks = new ArrayList<String>();

    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Window.setTitle(constants.stockWatcher());
        RootPanel.get("appTitle").add(new Label(constants.stockWatcher()));

        stocksFlexTable.setText(0, 0, constants.symbol());
        stocksFlexTable.setText(0, 1, constants.price());
        stocksFlexTable.setText(0, 2, constants.change());
        stocksFlexTable.setText(0, 3, constants.remove());

        stocksFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        stocksFlexTable.addStyleName("watchList");


        HorizontalPanel addPanel = new HorizontalPanel();
        addPanel.add(stockNameTxt);
        addPanel.add(addBStock);

        lastUpdatedLabel.setText(messages.lastUpdate(new Date()));


        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(stocksFlexTable);
        mainPanel.add(addPanel);
        mainPanel.add(lastUpdatedLabel);

        RootPanel.get("stockList").add(mainPanel);

        stockNameTxt.setFocus(true);

        addBStock.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                try {
                    addStock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addBStock.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    try {
                        addStock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        stockNameTxt.setFocus(true);
        // Setup timer to refresh list automatically.
        Timer refreshTimer = new Timer() {
            @Override
            public void run() {
                try {
                    refreshWatchList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        refreshTimer.scheduleRepeating(REFRESH_INTERVAL);

    }

    private void refreshWatchList() throws Exception {
        final double MAX_PRICE = 100.0; // $100.00
        final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

        StockPrice[] prices = new StockPrice[stocks.size()];
        for (int i = 0; i < stocks.size(); i++) {
            double price = Random.nextDouble() * MAX_PRICE;
            double change = price * MAX_PRICE_CHANGE
                    * (Random.nextDouble() * 2.0 - 1.0);

            prices[i] = new StockPrice(stocks.get(i), price, change);
        }

        updateTable(prices);

    }

    private void updateTable(StockPrice[] prices) throws Exception {
        for (int i = 0; i < prices.length; i++) {
            updateTable(prices[i]);
        }
        // Display timestamp showing last refresh.
        lastUpdatedLabel.setText(messages.lastUpdate(new Date()));
    }

    private void updateTable(StockPrice price) {
        int row = stocks.indexOf(price.getSymbol()) + 1;

        // Format the data in the Price and Change fields.
        String priceText = NumberFormat.getFormat("#,##0.00").format(
                price.getPrice());
        NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
        String changeText = changeFormat.format(price.getChange());
        String changePercentText = changeFormat.format(price.getChangePercent());

        //Label changeWidget = (Label) stocksFlexTable.getWidget(row, 2);

        //changeWidget.setText(changeText + "(" + changePercentText + "%)");
        String changeShowText = changeText + "(" + changePercentText + "%)";


        String changeStyle = "noChange";
        if(changeShowText.contains("+")) {
            changeStyle = "positiveChange";
        } else if(changeShowText.contains("-")) {
            changeStyle = "negativeChange";
        }
        stocksFlexTable.getCellFormatter().setStyleName(row, 2, changeStyle);
        stocksFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
        stocksFlexTable.setText(row, 1, priceText);
        stocksFlexTable.setText(row, 2, changeShowText);

    }

    private void addStock() throws Exception {
        final String symbol = stockNameTxt.getText().toUpperCase().trim();

        if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
            Window.alert(messages.invalidSymbol(symbol));
            stockNameTxt.selectAll();
            return;
        }

        if (stocks.contains(symbol)) {
            Window.alert(messages.existSymbol(symbol));
            stockNameTxt.selectAll();
            return;
        }

        stockNameTxt.setText("");

        int row = stocksFlexTable.getRowCount();
        stocks.add(symbol);


        Button removeStockBtn = new Button("x");
        removeStockBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int i = stocks.indexOf(symbol);
                stocks.remove(i);
                stocksFlexTable.removeRow(i + 1);
            }
        });

        stocksFlexTable.setText(row, 0, symbol);
        stocksFlexTable.setWidget(row, 3, removeStockBtn);


        stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
        stocksFlexTable.getCellFormatter().addStyleName(row, 3, "removeColumn");
        refreshWatchList();
    }
}
