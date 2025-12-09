import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PieChartOracleApp extends JFrame {

    private JTextField txtV1, txtV2, txtV3;
    private JPanel chartContainer;

    // ===== CONFIGURAR AQUÍ TU BD ORACLE =====
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "system";
    private static final String PASS = "Tapiero123";

    public PieChartOracleApp() {
        setTitle("Grafico Excel - Porcentajes (Oracle - 19C)");
        setSize(750, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // panel de entrada de datos
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5));

        inputPanel.add(new JLabel("Valor 1 (%):"));
        txtV1 = new JTextField("30");
        inputPanel.add(txtV1);

        inputPanel.add(new JLabel("Valor 2 (%):"));
        txtV2 = new JTextField("40");
        inputPanel.add(txtV2);

        inputPanel.add(new JLabel("Valor 3 (%):"));
        txtV3 = new JTextField("30");
        inputPanel.add(txtV3);

        JButton btnGenerar = new JButton("Generar Gráfico");
        JButton btnGuardar = new JButton("Guardar en Oracle");

        inputPanel.add(btnGenerar);
        inputPanel.add(btnGuardar);

        add(inputPanel, BorderLayout.NORTH);

        // contenedor del grafico . .
        chartContainer = new JPanel();
        add(chartContainer, BorderLayout.CENTER);

        // accion: generar grafico .
        btnGenerar.addActionListener(e -> generarGrafico ());

        // accion: guardar en oracle
        btnGuardar.addActionListener(e -> guardarOracle());
    }

    private void guardarOracle() {
    }

    private void generarGrafico() {
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}