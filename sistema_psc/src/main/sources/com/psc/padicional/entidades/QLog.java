package com.psc.padicional.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLog is a Querydsl query type for Log
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLog extends EntityPathBase<Log> {

    private static final long serialVersionUID = -1754284224L;

    public static final QLog log = new QLog("log");

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final StringPath details = createString("details");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath url = createString("url");

    public final StringPath username = createString("username");

    public QLog(String variable) {
        super(Log.class, forVariable(variable));
    }

    public QLog(Path<? extends Log> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLog(PathMetadata<?> metadata) {
        super(Log.class, metadata);
    }

}

