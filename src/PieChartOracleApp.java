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

    private void generarGrafico() {
        try {
            double v1 = Double.parseDouble(txtV1.getText());
            double v2 = Double.parseDouble(txtV2.getText());
            double v3 = Double.parseDouble(txtV3.getText());

            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Tema A (30%)", v1);
            dataset.setValue("Tema B (40%)", v2);
            dataset.setValue("Tema C (30%)", v3);

            JFreeChart chart = ChartFactory.createPieChart(
                    "Distribucion de porcentajes (Tipo Excel)",
                    dataset,
                    true,
                    true,
                    false
            );

            chartContainer.removeAll();
            chartContainer.add(new ChartPanel(chart));
            chartContainer.validate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar grafico: " + ex.getMessage());
        }
    }

    private void guardarOracle() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            String sql = "INSERT INTO CHART_DATA (VALUE1, VALUE2, VALUE3) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, Double.parseDouble(txtV1.getText()));
            ps.setDouble(2, Double.parseDouble(txtV2.getText()));
            ps.setDouble(3, Double.parseDouble(txtV3.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Datos guardados en Oracle correctamente.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Oracle: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PieChartOracleApp().setVisible(true));
    }
}