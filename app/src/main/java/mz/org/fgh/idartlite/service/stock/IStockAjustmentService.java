package mz.org.fgh.idartlite.service.stock;

import java.sql.SQLException;
import java.util.List;

import mz.org.fgh.idartlite.base.service.IBaseService;
import mz.org.fgh.idartlite.model.StockAjustment;
import mz.org.fgh.idartlite.model.inventory.Iventory;

public interface IStockAjustmentService extends IBaseService<StockAjustment> {
    List<StockAjustment> getAllOfInventory(Iventory iventory) throws SQLException;

}