import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmCajaRegistradora extends JFrame {
    JTable tblTablaDevolucion;
    JComboBox cmbOpciones;
    JTextField txtCantidad;
    JTextField txtCantDevolucion;

    Integer[] opciones = { 100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50 };
    String[] encabezados = { "Cantidad", "Presentación", "Denominción" };

    // metodo constructor
    public FrmCajaRegistradora() {
        setSize(500, 400);
        setTitle("Caja Registradora");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JTextArea txtDenomimacion = new JTextArea("Denominación");
        txtDenomimacion.setBounds(10, 10, 250, 50);
        txtDenomimacion.setEditable(false);
        txtDenomimacion.setOpaque(false);
        txtDenomimacion.setLineWrap(true);
        getContentPane().add(txtDenomimacion);

        cmbOpciones = new JComboBox(opciones);
        cmbOpciones.setBounds(150, 10, 100, 25);
        getContentPane().add(cmbOpciones);

        JButton btnActualizar = new JButton("Actualizar existencia");
        btnActualizar.setBounds(10, 50, 200, 25);
        getContentPane().add(btnActualizar);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(250, 50, 100, 25);
        getContentPane().add(txtCantidad);

        JTextArea txtValor = new JTextArea("Valor a devolver");
        txtValor.setBounds(10, 100, 250, 50);
        txtValor.setEditable(false);
        txtValor.setOpaque(false);
        txtValor.setLineWrap(true);
        getContentPane().add(txtValor);

        txtCantDevolucion = new JTextField();
        txtCantDevolucion.setBounds(100, 100, 100, 25);
        getContentPane().add(txtCantDevolucion);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(230, 100, 100, 25);
        getContentPane().add(btnDevolver);

        tblTablaDevolucion = new JTable();
        JScrollPane spTablaDevolucion = new JScrollPane(tblTablaDevolucion);
        spTablaDevolucion.setBounds(10, 150, 350, 200);
        getContentPane().add(spTablaDevolucion);

        // falta poner encabezado de la tabla
        DefaultTableModel dtm = new DefaultTableModel(null, encabezados);
        tblTablaDevolucion.setModel(dtm);

        JTextArea txtSaldo = new JTextArea("Tienes un saldo a favor");
        txtSaldo.setBounds(10, 350, 250, 50);
        txtSaldo.setEditable(false);
        txtSaldo.setOpaque(false);
        txtSaldo.setLineWrap(true);
        getContentPane().add(txtSaldo);

        JTextField txtValorSaldo = new JTextField();
        txtValorSaldo.setBounds(150, 350, 100, 25);
        txtValorSaldo.setEnabled(false);
        getContentPane().add(txtValorSaldo);

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCaja();
            }
            

        });

        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Devolucion();
            }

        });

    }

    int[] CantidadBilletes = new int[10];

    private void actualizarCaja() {
        /* String Denominaciones =  String.valueOf(cmbOpciones.getSelectedItem()); // aca se debe leer el vector de opciones
        for (int i=1;i<opciones.length;i++){
            if (opciones[i]==Denominaciones){
                int dato = Integer.parseInt(txtCantidad.getText());
                CantidadBilletes[i]=dato;
                }
            }*/
   }
    private void Devolucion() {
    }
}