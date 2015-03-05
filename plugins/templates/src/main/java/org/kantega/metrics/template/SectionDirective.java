package org.kantega.metrics.template;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.ASTStringLiteral;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 */
public class SectionDirective extends Directive {

    public String getName() {
        return "section";
    }

    public int getType() {
        return BLOCK;
    }

    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        final Node firstNode = node.jjtGetChild(0);
        String name;
        if(firstNode instanceof ASTStringLiteral) {
            ASTStringLiteral nameNode = (ASTStringLiteral) firstNode;
            name = nameNode.literal();

            name = name.substring(1, name.length()-1);
        } else if (firstNode instanceof ASTReference) {
            ASTReference nameNode = (ASTReference) node.jjtGetChild(0);
            StringWriter sw = new StringWriter();
            firstNode.render(context, sw);
            name = sw.toString();
        } else {
            throw new IllegalArgumentException("Unknown Velocity node type " + firstNode.getClass().getName());
        }

        StringWriter sw = new StringWriter();
        node.jjtGetChild(1).render(context, sw);
        context.put(name, sw.toString());
        return true;
    }
}