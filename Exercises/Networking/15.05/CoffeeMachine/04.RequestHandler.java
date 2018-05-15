package com.seeburger.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class RequestHandler implements Runnable
{
	// private final double tea = 0.40;
	// private final double coffee = 0.50;
	// private final double cappucino = 0.70;
	// private final double hotChocolate = 0.80;
	// private final double milk = 0.60;

	private static final ArrayList<Double> menu = new ArrayList<>(Arrays.asList(0.40, 0.50, 0.70, 0.80, 0.60));
	private Socket clientSocket;

	private double money;
	private int selection;

	public RequestHandler(Socket clientSocket)
	{
		this.clientSocket = clientSocket; //setva si socketa
	}

	@Override
	public void run()
	{
		processRequest();
	}
	
	private void processRequest()
	{
		try
		{
			DataInputStream inputStream = new DataInputStream(this.clientSocket.getInputStream());
			this.money = Double.parseDouble(inputStream.readUTF()); // tiq raoti idvat ot klienta
			this.selection = Integer.parseInt(inputStream.readUTF());

			DataOutputStream dataOutputStream = new DataOutputStream(this.clientSocket.getOutputStream()); 

			dataOutputStream.writeUTF(String.valueOf(isRequestPossible()));
			dataOutputStream.flush();

			if (isRequestPossible())
			{
				dataOutputStream.writeUTF("Processing your order..");
				
				// кодът долу с приспиване на нишката не е в условието и може да се махне
				try
				{
					Thread.currentThread().sleep(500);   
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				double check = this.money - menu.get(this.selection - 1);
				if (check > 0)
				{
					DecimalFormat dFormat = new DecimalFormat("####0.00");
					dataOutputStream.writeUTF("Change: $" + dFormat.format(check));
				}
				dataOutputStream.flush();
			}
			// this.clientSocket.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private boolean isRequestPossible()
	{
		if (this.money - menu.get(this.selection - 1) >= 0)
		{
			return true;
		}
		return false;
	}
}
