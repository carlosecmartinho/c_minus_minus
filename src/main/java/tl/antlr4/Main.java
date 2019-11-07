package tl.antlr4;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        try {
            CminusLexer lexer = new CminusLexer(CharStreams.fromFileName("/Users/martinhoc/Documents/code/compiladores/c_minus_minus/src/main/tl/test_2.tl"));
            CminusParser parser = new CminusParser(new CommonTokenStream(lexer));
            parser.setBuildParseTree(true);
            ParseTree tree = parser.parse();
            System.out.println(tree.toStringTree());
            // System.out.println("Rodouuu");
            Scope scope = new Scope();
            Map<String, Function> functions = new HashMap<>();
            SymbolVisitor symbolVisitor = new SymbolVisitor(functions);
            symbolVisitor.visit(tree);
            EvalVisitor visitor = new EvalVisitor(scope, functions);
            visitor.visit(tree);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
    }
}
