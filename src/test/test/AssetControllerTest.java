import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.AssetController;
import org.example.module.entity.Asset;
import org.example.service.AssetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AssetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    @Test
    void testCreateAsset() throws Exception {
        Asset asset = new Asset();
        asset.setCurrencyCode("USD");

        when(assetService.createAsset(asset)).thenReturn(asset);

        mockMvc = MockMvcBuilders.standaloneSetup(assetController).build();

        mockMvc.perform(post("/assets/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(asset)))
                .andExpect(status().isCreated());
    }
}
