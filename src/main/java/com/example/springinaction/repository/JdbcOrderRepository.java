//package com.example.springinaction.repository;
//
//import com.example.springinaction.dao.Ingredient;
//import com.example.springinaction.dao.IngredientRef;
//import com.example.springinaction.dao.Taco;
//import com.example.springinaction.dao.TacoOrder;
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//public class JdbcOrderRepository implements OrderRepository {
//
//  private final JdbcOperations jdbcOperations;
//
//  @Autowired
//  public JdbcOrderRepository(JdbcOperations jdbcOperations) {
//    this.jdbcOperations = jdbcOperations;
//  }
//
//  @Override
//  @Transactional
//  public TacoOrder save(TacoOrder order) {
//    PreparedStatementCreatorFactory pscf =
//        new PreparedStatementCreatorFactory(
//            "insert into Taco_Order "
//                + "(delivery_name, delivery_street, delivery_city, "
//                + "delivery_state, delivery_zip, cc_number, "
//                + "cc_expiration, cc_cvv, placed_at) "
//                + "values (?,?,?,?,?,?,?,?,?)",
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.TIMESTAMP);
//    pscf.setReturnGeneratedKeys(true);
//    order.setPlacedAt(new Date());
//    PreparedStatementCreator psc =
//        pscf.newPreparedStatementCreator(
//            Arrays.asList(
//                order.getDeliveryName(),
//                order.getDeliveryStreet(),
//                order.getDeliveryCity(),
//                order.getDeliveryState(),
//                order.getDeliveryZip(),
//                order.getCcNumber(),
//                order.getCcExpiration(),
//                order.getCcCVV(),
//                order.getPlacedAt()));
//    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//    jdbcOperations.update(psc, keyHolder);
//    long orderId = Objects.requireNonNull(keyHolder.getKey()).longValue();
//    order.setId(orderId);
//
//    List<Taco> tacos = order.getTacos();
//    int i = 0;
//    for (Taco taco : tacos) {
//      saveTaco(orderId, i++, taco);
//    }
//    return order;
//  }
//
//  private long saveTaco(Long orderId, int orderKey, Taco taco) {
//    taco.setCreatedAt(new Date());
//    PreparedStatementCreatorFactory pscf =
//        new PreparedStatementCreatorFactory(
//            "insert into Taco "
//                + "(name, created_at, taco_order, taco_order_key) "
//                + "values(?, ?, ?, ?)",
//            Types.VARCHAR,
//            Types.TIMESTAMP,
//            Types.BIGINT,
//            Types.BIGINT);
//    pscf.setReturnGeneratedKeys(true);
//
//    PreparedStatementCreator psc =
//        pscf.newPreparedStatementCreator(
//            Arrays.asList(taco.getName(), taco.getCreatedAt(), orderId, orderKey));
//    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//    jdbcOperations.update(psc, keyHolder);
//    long tacoId = Objects.requireNonNull(keyHolder.getKey()).longValue();
//    taco.setId(tacoId);
//
//    saveTacoIngredients(tacoId,taco.getIngredients());
//    return tacoId;
//  }
//
//  private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
//    int key = 0;
//    for (IngredientRef ingredientRef : ingredientRefs)
//      jdbcOperations.update(
//          "insert into Ingredient_Ref (ingredient, taco, taco_key) " + "values (?, ?, ?)",
//          ingredientRef.getIngredient(),
//          tacoId,
//          key++);
//  }
//  private void saveTacoIngredients(long tacoId, List<Ingredient> ingredients) {
//    List<IngredientRef> ingredientRefs = ingredients.stream()
//            .map(ingredient -> new IngredientRef(ingredient.getId()))
//            .collect(Collectors.toList());
//
//    saveIngredientRefs(tacoId, ingredientRefs);
//  }
//}
