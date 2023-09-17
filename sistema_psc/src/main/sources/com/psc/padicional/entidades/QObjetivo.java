package com.psc.padicional.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjetivo is a Querydsl query type for Objetivo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QObjetivo extends EntityPathBase<Objetivo> {

    private static final long serialVersionUID = -1550028608L;

    public static final QObjetivo objetivo = new QObjetivo("objetivo");

    public final BooleanPath arma = createBoolean("arma");

    public final BooleanPath civilFormal = createBoolean("civilFormal");

    public final StringPath consigna = createString("consigna");

    public final StringPath descripcion = createString("descripcion");

    public final BooleanPath domingo = createBoolean("domingo");

    public final StringPath estado = createString("estado");

    public final BooleanPath feriados = createBoolean("feriados");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idEnte = createNumber("idEnte", Integer.class);

    public final BooleanPath jueves = createBoolean("jueves");

    public final BooleanPath lunes = createBoolean("lunes");

    public final BooleanPath martes = createBoolean("martes");

    public final BooleanPath miercoles = createBoolean("miercoles");

    public final BooleanPath sabado = createBoolean("sabado");

    public final BooleanPath traje = createBoolean("traje");

    public final BooleanPath uniforme = createBoolean("uniforme");

    public final BooleanPath viernes = createBoolean("viernes");

    public QObjetivo(String variable) {
        super(Objetivo.class, forVariable(variable));
    }

    public QObjetivo(Path<? extends Objetivo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QObjetivo(PathMetadata<?> metadata) {
        super(Objetivo.class, metadata);
    }

}

