package com.psc.padicional.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCategoria is a Querydsl query type for Categoria
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCategoria extends EntityPathBase<Categoria> {

    private static final long serialVersionUID = -2061168593L;

    public static final QCategoria categoria = new QCategoria("categoria");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nombre = createString("nombre");

    public final NumberPath<Float> valorHora = createNumber("valorHora", Float.class);

    public QCategoria(String variable) {
        super(Categoria.class, forVariable(variable));
    }

    public QCategoria(Path<? extends Categoria> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoria(PathMetadata<?> metadata) {
        super(Categoria.class, metadata);
    }

}

