package psp.procesos.gestoralumnos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorAlumnos extends JFrame implements ActionListener {

    private JLabel etiqueta;
    private JTextField campoTexto;
    private JButton boton;

    public GestorAlumnos(){
        super();
        configurarGUI();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        etiqueta = new JLabel();
        campoTexto = new JTextField();
        boton = new JButton();

        etiqueta.setText("<html><b><i>Añadir Alumno</i></b></html>");
        etiqueta.setBounds(50,100,200,25);

        campoTexto.setBounds(50,150,200,20);

        boton.setText("Buscar Usuario");
        boton.setBounds(50,200,200,25);
        boton.addActionListener(this);
        boton.setActionCommand("Buscar");


        this.add(etiqueta);
        this.add(campoTexto);
        this.add(boton);

        JPanel panel = (JPanel) this.getContentPane();
        panel.revalidate();
        panel.repaint();
    }

    private void configurarGUI(){
        this.setTitle("Gestor de Alumnos");
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();

        if (comando.equalsIgnoreCase("buscar")){
            String texto = campoTexto.getText();
            //JOptionPane.showConfirmDialog(this,"Boton Pulsado. Texto: " + texto);
            JOptionPane.showMessageDialog(this,"Boton Pulsado. Texto: " + texto);
        }
    }

    public static void main(String[] args) {
        GestorAlumnos gestorAlumnos = new GestorAlumnos();
    }


}
