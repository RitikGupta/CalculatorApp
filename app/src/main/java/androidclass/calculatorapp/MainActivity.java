package androidclass.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    TextView txtViewResult , txtViewUserInput;
    Button btnClear, btnBackspace, btnDivide,btnMultiply,btn7,btn8,btn9,btnMinus,btn4, btn5,btn6,btnPlus,btn1,btn2,btn3,btnEqual,
            btnZero,btnDoubleZero,btnDecimal;

    private TokenStack operatorStack;
    private TokenStack valueStack;
    private boolean error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operatorStack = new TokenStack();
        valueStack = new TokenStack();
        error = false;

        txtViewUserInput = (TextView)findViewById(R.id.textView_userInput);
        txtViewResult = (TextView)findViewById(R.id.textViewResult);

        btnClear =  (Button)findViewById(R.id.btnClear);
        btnBackspace =  (Button)findViewById(R.id.btnBksp);
        btnDivide = (Button)findViewById(R.id.btnDivide);
        btnMultiply = (Button)findViewById(R.id.btnMultiply);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnDecimal = (Button)findViewById(R.id.btnDecimal);
        btnZero =  (Button)findViewById(R.id.btnZero);
        btnDoubleZero = (Button)findViewById(R.id.btnDoubleZero);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText("");
                txtViewResult.setText("");
            }
        });

        btnBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txtViewUserInput.getText().toString();
                if(s.length() == 0)
                    txtViewUserInput.setText("");
                else if(s.charAt(s.length() - 1) == ' ')  //means there is a operator at last
                {
                    s = s.substring(0 , s.lastIndexOf(' ') - 2);
                    txtViewUserInput.setText(s);
                    double result = processInput(txtViewUserInput.getText().toString());
                    String ans = String.format("%.2f", result);
                    txtViewResult.setText(ans);
                }
                else //means there is a digit or decimal at last
                {
                    s = s.substring(0 , s.length() - 1);
                    txtViewUserInput.setText(s);

                    if(s.charAt(s.length() - 1) == ' ') //means there is a operator at last now so sending input str before operator
                        s = s.substring(0 , s.lastIndexOf(' ') - 2);

                    double result = processInput(s);
                    String ans = String.format("%.2f", result);
                    txtViewResult.setText(ans);
                }

            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txtViewUserInput.getText().toString();
                if(s.length() == 0)
                    txtViewUserInput.setText("");
                else if(s.charAt(s.length() - 1) != ' ')
                    txtViewUserInput.setText(s + " / ");//s.charAt(s.length() - 1) != ' ') is to check that it's the first operator, as in that case we won't overwrite it
                else
                {
                    s = s.substring(0 , s.lastIndexOf(' ') - 2);
                    txtViewUserInput.setText(s + " / ");
                }
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txtViewUserInput.getText().toString();
                if(s.length() == 0)
                    txtViewUserInput.setText("");
                else if(s.charAt(s.length() - 1) != ' ')
                    txtViewUserInput.setText(s + " * ");//s.charAt(s.length() - 1) != ' ') is to check that it's the first operator, as in that case we won't overwrite it
                else
                {
                    s = s.substring(0 , s.lastIndexOf(' ') - 2);
                    txtViewUserInput.setText(s + " * ");
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "7");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "8");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "9");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txtViewUserInput.getText().toString();
                if(s.length() == 0)
                    txtViewUserInput.setText("");
                else if(s.charAt(s.length() - 1) != ' ')
                    txtViewUserInput.setText(s + " - ");//s.charAt(s.length() - 1) != ' ') is to check that it's the first operator, as in that case we won't overwrite it
                else
                {
                    s = s.substring(0 , s.lastIndexOf(' ') - 2);
                    txtViewUserInput.setText(s + " - ");
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "4");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "5");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "6");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txtViewUserInput.getText().toString();
                if(s.length() == 0)
                    txtViewUserInput.setText("");
                else if(s.charAt(s.length() - 1) != ' ')
                    txtViewUserInput.setText(s + " + ");//s.charAt(s.length() - 1) != ' ') is to check that it's the first operator, as in that case we won't overwrite it
                else
                {
                    s = s.substring(0 , s.lastIndexOf(' ') - 2);
                    txtViewUserInput.setText(s + " + ");
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "1");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "2");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "3");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txtViewUserInput.getText().toString();
                char[] chArr = s.toCharArray();

                int n = chArr.length;

                int i = n-1;
                boolean flag = true;

                while((i >= 0) && (chArr[i] != ' '))
                {
                    if(chArr[i] == '.')
                    {
                        flag = false;
                        break;
                    }
                    i--;
                }
                if(flag)
                    s += '.';
               
                txtViewUserInput.setText(s);
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "0");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btnDoubleZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewUserInput.setText(txtViewUserInput.getText().toString() + "00");
                double result = processInput(txtViewUserInput.getText().toString());
                String ans = String.format("%.2f", result);
                txtViewResult.setText(ans);
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* double result = processInput(txtViewUserInput.getText().toString());
                txtViewResult.setText(String.valueOf(result));*/
                txtViewUserInput.setText(txtViewResult.getText().toString());
                txtViewResult.setText("");

            }
        });

    }

    private void processOperator(Token t) {
        Token A = null, B = null;
        if (valueStack.isEmpty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            B = valueStack.top();
            valueStack.pop();
        }
        if (valueStack.isEmpty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            A = valueStack.top();
            valueStack.pop();
        }
        Token R = t.operate(A.getValue(), B.getValue());
        valueStack.push(R);
    }

    public double processInput(String input) {
        // The tokens that make up the input
        String[] parts = input.split(" ");
        Token[] tokens = new Token[parts.length];
        for (int n = 0; n < parts.length; n++) {
            tokens[n] = new Token(parts[n]);
        }

        // Main loop - process all input tokens
        for (int n = 0; n < tokens.length; n++) {
            Token nextToken = tokens[n];
            if (nextToken.getType() == Token.NUMBER) {
                valueStack.push(nextToken);
            } else if (nextToken.getType() == Token.OPERATOR) {
                if (operatorStack.isEmpty() || nextToken.getPrecedence() > operatorStack.top().getPrecedence()) {
                    operatorStack.push(nextToken);
                } else {
                    while (!operatorStack.isEmpty() && nextToken.getPrecedence() <= operatorStack.top().getPrecedence()) {
                        Token toProcess = operatorStack.top();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    operatorStack.push(nextToken);
                }
            } else if (nextToken.getType() == Token.LEFT_PARENTHESIS) {
                operatorStack.push(nextToken);
            } else if (nextToken.getType() == Token.RIGHT_PARENTHESIS) {
                while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
                    Token toProcess = operatorStack.top();
                    operatorStack.pop();
                    processOperator(toProcess);
                }
                if (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.LEFT_PARENTHESIS) {
                    operatorStack.pop();
                } else {
                    System.out.println("Error: unbalanced parenthesis.");
                    error = true;
                }
            }

        }
        // Empty out the operator stack at the end of the input
        while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
            Token toProcess = operatorStack.top();
            operatorStack.pop();
            processOperator(toProcess);
        }
        // Print the result if no error has been seen.
        if(error == false) {
            Token result = valueStack.top();
            valueStack.pop();
            if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
                System.out.println("Expression error.");
            } else {
                 return result.getValue();
            }
        }
        return 0;
    }
}
