package org.isaac.lineage.neo4j.domain.node;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.isaac.lineage.neo4j.contants.NeoConstant;
import org.neo4j.ogm.annotation.Properties;

/**
 * <p>
 * Unified abstraction of graph relationships and node entities
 * </p>
 *
 * @author JupiterMouse 2020/09/27
 * @since 1.0
 */
@Setter
@Getter
public abstract class BaseEntity {
    /**
     * Figure real ID，Generated by neo4j engine，Can't be modified
     */
    private Long id;

    /**
     * Node extra value, starting with ext prefix
     */
    @Properties(prefix = NeoConstant.ENTITY_EXTRA_PREFIX)
    private Map<String, String> extra;
}
