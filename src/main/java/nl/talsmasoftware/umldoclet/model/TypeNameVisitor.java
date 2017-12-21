/*
 * Copyright 2016-2017 Talsma ICT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.talsmasoftware.umldoclet.model;

import javax.lang.model.element.Element;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.type.*;
import javax.lang.model.util.SimpleTypeVisitor9;

/**
 * The UML type name implemented as {@link TypeVisitor}.
 *
 * @author Sjoerd Talsma
 */
// TODO javadoc aware code.
final class TypeNameVisitor extends SimpleTypeVisitor9<TypeName, Void> {

    static final TypeNameVisitor INSTANCE = new TypeNameVisitor();

    private TypeNameVisitor() {
    }

    @Override
    public TypeName visitPrimitive(PrimitiveType primitiveType, Void parameter) {
        // "byte", "char", "short", "int", "long", "float", "double", "boolean"
        final String primitive = primitiveType.getKind().name().toLowerCase();
        return new TypeName(primitive, primitive);
    }

    @Override
    public TypeName visitNoType(NoType noType, Void parameter) {
        // "void", "package", "module", "none"
        final String none = noType.getKind().name().toLowerCase();
        return new TypeName(none, none);
    }

    @Override
    public TypeName visitDeclared(DeclaredType declaredType, Void parameter) {
        final Element el = declaredType.asElement();
        final String simpleName = el.getSimpleName().toString();
        final String qualifiedName = el instanceof QualifiedNameable
                ? ((QualifiedNameable) el).getQualifiedName().toString() : simpleName;
        final TypeName[] generics = declaredType.getTypeArguments().stream()
                .map(generic -> visit(generic, parameter))
                .toArray(TypeName[]::new);
        return new TypeName(simpleName, qualifiedName, generics);
    }

    @Override
    public TypeName visitArray(ArrayType arrayType, Void parameter) {
        return TypeName.Array.of(visit(arrayType.getComponentType()));
    }

//    @Override
//    public TypeName visitTypeVariable(TypeVariable typeVariable, Void parameter) {
//        // TODO handle type variables better!
//        return new TypeName(typeVariable.toString(), typeVariable.toString());
//    }

    @Override
    protected TypeName defaultAction(TypeMirror tp, Void parameter) {
        // TODO handle unknown type variables better!
        return new TypeName(tp.toString(), tp.toString());
    }

    // TODO Figure out how intersections and unions are represented in UML
//    @Override
//    public String visitIntersection(IntersectionType intersectionType, Void parameter) {
//        return intersectionType.getBounds().stream()
//                .map(bound -> visit(bound, parameter))
//                .collect(joining(" & "));
//    }

//    @Override
//    public String visitUnion(UnionType unionType, Void parameter) {
//        return unionType.getAlternatives().stream()
//                .map(alternative -> visit(alternative, parameter))
//                .collect(joining(" | "));
//    }

}
