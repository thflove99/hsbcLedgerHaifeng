package org.example.service;

import org.example.module.entity.Asset;
import java.util.List;

/**
 * @Author Fox
 * @Date 2024/4/8
 */
public interface IAssetService {
     Asset createAsset(Asset asset);

     List<Asset> findAll();
}
