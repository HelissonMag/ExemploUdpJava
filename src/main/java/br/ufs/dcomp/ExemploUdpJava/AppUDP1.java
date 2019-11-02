package br.ufs.dcomp.ExemploUdpJava;

import java.net.*;
import java.util.*;
public class AppUDP1 {

    public static void main(String[] args) throws SocketException {
        try{
            System.out.print("[ Alocando porta UDP      ..................  ");
    	    DatagramSocket socket = new DatagramSocket(10000);
            System.out.println("[OK] ]");
            Scanner e = new Scanner(System.in);
            String msg;
            while(true){
                
                msg = e.nextLine();
                
                byte[] msg_buf = msg.getBytes();
                int msg_size = msg_buf.length;
                InetAddress destination_address = InetAddress.getLocalHost();
                int destination_port = 20000; 
    
                System.out.print("[ Montando datagrama UDP  ..................  ");
                DatagramPacket pack = new DatagramPacket(msg_buf, msg_size, destination_address, destination_port);
                System.out.println("[OK] ]");
                
                System.out.print("[ Enviando datagrama UDP  ..................  ");
                socket.send(pack);
                System.out.println("[OK] ]");
                
                byte[] buff = new byte[20];
                DatagramPacket pack3 = new DatagramPacket(buff, buff.length);
                
                System.out.print("[ Aguardando recebimento da resposta ..................  ");
                socket.receive(pack3);
                System.out.println("[OK] ]");
                
                byte[] received_data = pack3.getData();
                String received_msg = new String(received_data); 
                InetAddress origin_address = pack3.getAddress();
                int origin_port = pack3.getPort();
                
                System.out.println("  Resposta:             "+received_msg);
                System.out.println("  Endereço de origem:   "+origin_address.getHostAddress());
                System.out.println("  Porta de origem:      "+origin_port);
            
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }    
    }
}