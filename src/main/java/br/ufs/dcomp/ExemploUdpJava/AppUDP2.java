package br.ufs.dcomp.ExemploUdpJava; 

import java.net.*;
import java.util.*;
public class AppUDP2 {

    public static void main(String[] args) throws SocketException {
        try{
            Scanner es = new Scanner(System.in);
            String resp;
            System.out.print("[ Alocando porta UDP                  ..................  ");
    	    DatagramSocket socket = new DatagramSocket(20000);
            System.out.println("[OK] ]");
            while(true){
                byte[] buf = new byte[20];
                DatagramPacket pack = new DatagramPacket(buf, buf.length);
            
                System.out.print("[ Aguardando recebimento de mensagem  ..................  ");
                socket.receive(pack);
                System.out.println("[OK] ]");
                
                byte[] received_data = pack.getData();
                String received_msg = new String(received_data); 
                InetAddress origin_address = pack.getAddress();
                int origin_port = pack.getPort();
                
                System.out.println("  Mensagem:             "+received_msg);
                System.out.println("  Endereço de origem:   "+origin_address.getHostAddress());
                System.out.println("  Porta de origem:      "+origin_port);
                
                resp = es.nextLine();
                byte[] resp_buf = resp.getBytes();
                int resp_size = resp_buf.length;
                InetAddress destination_address = InetAddress.getLocalHost();
      
                System.out.print("[ Montando datagrama UDP  ..................  ");
                DatagramPacket pack2 = new DatagramPacket(resp_buf, resp_size, destination_address, origin_port);
                System.out.println("[OK] ]");
                
                System.out.print("[ Enviando datagrama UDP (Resposta) ..................  ");
                socket.send(pack2);
                System.out.println("[OK] ]");
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }    
        
        
        
        

    }
}