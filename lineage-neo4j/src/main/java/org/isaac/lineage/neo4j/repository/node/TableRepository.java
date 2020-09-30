package org.isaac.lineage.neo4j.repository.node;

import org.isaac.lineage.neo4j.domain.node.TableNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/9/16 10:54
 * @since 1.0.0
 */
@Repository
public interface TableRepository extends Neo4jRepository<TableNode, String> {

    /**
     * FIELD_FROM_TABLE
     */
    @Query("MATCH (field:Field),(table:Table) " +
            "WHERE field.platformName = table.platformName " +
            "AND field.clusterName = table.clusterName " +
            "AND field.schemaName = table.schemaName " +
            "AND field.tableName = table.tableName " +
            "CREATE (field)-[:FIELD_FROM_TABLE]->(table)")
    void createRelationshipWithField();

    /**
     * FIELD_FROM_TABLE
     */
    @Query("MATCH (field:Field)-[r:FIELD_FROM_TABLE]->(table:Table) " +
            "WHERE field.platformName = table.platformName " +
            "AND field.clusterName = table.clusterName " +
            "AND field.schemaName = table.schemaName " +
            "AND field.tableName = table.tableName " +
            "DELETE r")
    void deleteRelationshipWithField();

    /**
     * CREATE_TABLE_AS_SELECT
     */
    @Query("MATCH (table1:Table),(table2:Table) " +
            "WHERE table1.platformName = table2.platformName " +
            "AND table1.clusterName = table2.clusterName " +
            "AND table1.schemaName = table2.schemaName " +
            "AND table1.createTableFrom = table2.pk " +
            "CREATE (table1)-[:CREATE_TABLE_AS_SELECT]->(table2)")
    void createRelationshipCreateTableAsSelect();

    /**
     * CREATE_TABLE_AS_SELECT
     */
    @Query("MATCH (table1:Table)-[r:CREATE_TABLE_AS_SELECT]->(table2:Table) " +
            "WHERE table1.platformName = table2.platformName " +
            "AND table1.clusterName = table2.clusterName " +
            "AND table1.schemaName = table2.schemaName " +
            "AND table1.createTableFrom = table2.pk " +
            "DELETE r")
    void deleteRelationshipCreateTableAsSelect();

    /**
     * INSERT_OVERWRITE_TABLE_SELECT
     */
    @Query("MATCH (table1:Table),(table2:Table) " +
            "WHERE table1.platformName = table2.platformName " +
            "AND table1.clusterName = table2.clusterName " +
            "AND table1.schemaName = table2.schemaName " +
            "AND table1.insertOverwriteFrom = table2.pk " +
            "CREATE (table1)-[:INSERT_OVERWRITE_TABLE_SELECT]->(table2)")
    void createRelationshipInsertOverwriteTableSelect();

    /**
     * INSERT_OVERWRITE_TABLE_SELECT
     */
    @Query("MATCH (table1:Table)-[r:INSERT_OVERWRITE_TABLE_SELECT]->(table2:Table) " +
            "WHERE table1.platformName = table2.platformName " +
            "AND table1.clusterName = table2.clusterName " +
            "AND table1.schemaName = table2.schemaName " +
            "AND table1.insertOverwriteFrom = table2.pk " +
            "DELETE r")
    void deleteRelationshipInsertOverwriteTableSelect();
}
