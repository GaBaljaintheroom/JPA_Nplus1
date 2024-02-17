package com.example.jpafetch.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSponsor is a Querydsl query type for Sponsor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSponsor extends EntityPathBase<Sponsor> {

    private static final long serialVersionUID = -1223014131L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSponsor sponsor = new QSponsor("sponsor");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTeam team;

    public QSponsor(String variable) {
        this(Sponsor.class, forVariable(variable), INITS);
    }

    public QSponsor(Path<? extends Sponsor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSponsor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSponsor(PathMetadata metadata, PathInits inits) {
        this(Sponsor.class, metadata, inits);
    }

    public QSponsor(Class<? extends Sponsor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

