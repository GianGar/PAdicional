package com.psc.padicional.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEnte is a Querydsl query type for Ente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEnte extends EntityPathBase<Ente> {

    private static final long serialVersionUID = 1451554910L;

    public static final QEnte ente = new QEnte("ente");

    public final StringPath categoria = createString("categoria");

    public final StringPath direccion = createString("direccion");

    public final StringPath estado = createString("estado");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nombre = createString("nombre");

    public final StringPath responsable = createString("responsable");

    public final StringPath telefono = createString("telefono");

    public final StringPath tipo = createString("tipo");

    public QEnte(String variable) {
        super(Ente.class, forVariable(variable));
    }

    public QEnte(Path<? extends Ente> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEnte(PathMetadata<?> metadata) {
        super(Ente.class, metadata);
    }

}

