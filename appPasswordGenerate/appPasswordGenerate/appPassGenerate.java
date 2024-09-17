package appPasswordGenerate;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Classe que implementa um gerador de palavras-passe (senhas) com interface gráfica.
 * O programa permite ao usuário especificar o número de caracteres da senha desejada,
 * dentro das especificaçoes pre prontas do programa.
 * Também inclui opções para fechar a aplicação e um checkbox com um valor pré-definido.
 */
public class appPassGenerate extends JFrame {
    // Variáveis para utilização no JFrame / Encapsulamento
    private JPanel checkBoxPanel;
    private JCheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    private JTextField resultField;
    private Map<JCheckBox, Integer> checkBoxMap;

    public appPassGenerate() {
        // Criação e setup da JFrame
        setTitle("Gerador de Palavras Passe!");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação da Label com a pergunta e centralização dela dentro do JPanel
        JPanel lbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbPanel.add(new JLabel("Qual o tamanho de senha deseja criar?"));
        add(lbPanel, BorderLayout.NORTH);

        // Checkbox para seleção de números já pré-definidos, dentro da checkBoxPanel que abaixo é criada.
        checkBoxPanel = new JPanel(new GridLayout(5, 2));
        add(checkBoxPanel, BorderLayout.CENTER);

        checkBoxMap = new HashMap<>();

        // 4 Caracteres
        checkBox1 = new JCheckBox("4 Caracteres");
        checkBoxPanel.add(checkBox1);
        checkBoxMap.put(checkBox1, 4);
        // 6 Caracteres
        checkBox2 = new JCheckBox("6 Caracteres");
        checkBoxPanel.add(checkBox2);
        checkBoxMap.put(checkBox2, 6);
        // 8 Caracteres
        checkBox3 = new JCheckBox("8 Caracteres");
        checkBoxPanel.add(checkBox3);
        checkBoxMap.put(checkBox3, 8);
        // 12 Caracteres
        checkBox4 = new JCheckBox("12 Caracteres");
        checkBoxPanel.add(checkBox4);
        checkBoxMap.put(checkBox4, 12);
        // 16 Caracteres
        checkBox5 = new JCheckBox("16 Caracteres");
        checkBoxPanel.add(checkBox5);
        checkBoxMap.put(checkBox5, 16);
        // 24 Caracteres
        checkBox6 = new JCheckBox("24 Caracteres");
        checkBoxPanel.add(checkBox6);
        checkBoxMap.put(checkBox6, 24);

        /*
         * Especificações mais detalhadas da password que deseja criar.
         */
        checkBox7 = new JCheckBox("Letras");
        checkBoxPanel.add(checkBox7);
        checkBox8 = new JCheckBox("Números");
        checkBoxPanel.add(checkBox8);
        checkBox9 = new JCheckBox("Especiais");
        checkBoxPanel.add(checkBox9);
        checkBox10 = new JCheckBox("Maiúsculas");
        checkBoxPanel.add(checkBox10);

        // Add item listeners to checkboxes
        ItemListener itmListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Desseleciona todos os outros checkboxes de tamanho
                    JCheckBox selectedCheckBox = (JCheckBox) e.getSource();
                    unselectAllExcept(selectedCheckBox);
                }
            }
        };
        checkBox1.addItemListener(itmListener);
        checkBox2.addItemListener(itmListener);
        checkBox3.addItemListener(itmListener);
        checkBox4.addItemListener(itmListener);
        checkBox5.addItemListener(itmListener);
        checkBox6.addItemListener(itmListener);

        // Criação do campo resultado, o qual mostrará o resultado do que se deseja gerar.
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField, BorderLayout.NORTH);  // Mudar para BorderLayout.NORTH

        /*
         * Criação do painel/lugar para o botão fechar.
         * Criação do botão de fechar, seus parâmetros e sua inclusão na JFrame.
         */
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnClose = new JButton("Close");
        btnClose.setPreferredSize(new java.awt.Dimension(70, 30));
        btnClose.addActionListener(e -> System.exit(0));
        btnPanel.add(btnClose);

        JButton btnGenerate = new JButton("Gerar Senha");
        btnGenerate.setPreferredSize(new java.awt.Dimension(120, 30));
        btnGenerate.addActionListener(e -> passGenerate());
        btnPanel.add(btnGenerate);

        add(btnPanel, BorderLayout.SOUTH);
    }

    private void unselectAllExcept(JCheckBox selectedCheckBox) {
        if (checkBox1 != selectedCheckBox) checkBox1.setSelected(false);
        if (checkBox2 != selectedCheckBox) checkBox2.setSelected(false);
        if (checkBox3 != selectedCheckBox) checkBox3.setSelected(false);
        if (checkBox4 != selectedCheckBox) checkBox4.setSelected(false);
        if (checkBox5 != selectedCheckBox) checkBox5.setSelected(false);
        if (checkBox6 != selectedCheckBox) checkBox6.setSelected(false);
    }

    private void passGenerate() {
        int length = 0;

        for (Map.Entry<JCheckBox, Integer> entry : checkBoxMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                length = entry.getValue();
                break;
            }
        }

        if (length > 0) {
            String password = generateRandomPassword(length);
            resultField.setText(password);
        } else {
            resultField.setText("Selecione um Tamanho de Senha.");
        }
    }

    private String generateRandomPassword(int length) {
    	StringBuilder password = new StringBuilder();
    	SecureRandom random = new SecureRandom();
    	
    	//definido o conjunto de caracteres a ser utilizados para montar as palavras passes
    	
    	String letras= "abcdefghijklmnopqrstuvwxyz";
    	String numeros = "0123456789";
    	String especiais = "!@#$%^&*()-_=+";
    	String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	
    	// Esse é o conjunto de caracteres para gerar a senha
    	Set<Character> charSet = new HashSet<>();	
    	 // Adicionar caracteres conforme selecionado nos checkboxes
        if (checkBox7.isSelected()) {
            for (char c : letras.toCharArray()) {
                charSet.add(c);
            }
        }
        if (checkBox8.isSelected()) {
            for (char c : numeros.toCharArray()) {
                charSet.add(c);
            }
        }
        if (checkBox9.isSelected()) {
            for (char c : especiais.toCharArray()) {
                charSet.add(c);
            }
        }
        if (checkBox10.isSelected()) {
            for (char c : maiusculas.toCharArray()) {
                charSet.add(c);
            }
        }
        //Gerar a senha através desse for aqui. 
        //lembrando que charSet contem cada caracter dentro da selecao selecionada pelo utilizador
        for(int i=0;i<length; i++) {//esse loop é responsável por iterar quantas vezes o length determinar
        	int randomIndex = random.nextInt(charSet.size());//gera o um numero aleatorio entre 0(inclusivo) e charSet.siza() (exclusivo)
        	int currentIndex = 0;//iniciamos a 0 para rastrear a posição atual durante as iteraçõespelo charset
        	for(char c :charSet) {//aqui um loop "for-each" em 'c' com as escolhas dentro do charSet.
        		if(currentIndex==randomIndex) {//Dentro do loop for-each, comparamos currentIndex com randomIndex. 
        			//Quando currentIndex é igual a randomIndex, significa que encontramos o caractere aleatório que queremos adicionar à senha.
        			password.append(c);//apos encontrado o caracter, e adicionado ao final da variavel 'c'.
        			break;
        		}
        		currentIndex++;
        	}
        }
        return password.toString();

    }

    /*
     * Main que chama a classe e faz ela aparecer , ou seja, o programa rodar.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new appPassGenerate().setVisible(true);
        });
    }
}
