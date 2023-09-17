package com.psc.padicional.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFeriado is a Querydsl query type for Feriado
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFeriado extends EntityPathBase<Feriado> {

    private static final long serialVersionUID = -2125169742L;

    public static final QFeriado feriado = new QFeriado("feriado");

    public final StringPath descripcion = createString("descripcion");

    public final StringPath fecha = createString("fecha");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QFeriado(String variable) {
        super(Feriado.class, forVariable(variable));
    }

    public QFeriado(Path<? extends Feriado> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeriado(PathMetadata<?> metadata) {
        super(Feriado.class, metadata);
    }

}

