package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.module.entity.Asset;
import org.example.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
@Api(tags = "assets API", description = "Operations related to assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/create")
    @ApiOperation(value = "Create a new assets", response = Asset.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "assets created successfully"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset createdAsset = assetService.createAsset(asset);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @GetMapping("/query/all")
    @ApiOperation(value = "show all the assets", response = Asset.class)
    public ResponseEntity<List<Asset>> getWalletsByAccountId() {
        List<Asset> assets = assetService.findAll();
        if (assets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }
}
