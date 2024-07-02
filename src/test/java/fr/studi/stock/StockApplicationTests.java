package fr.studi.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.studi.stock.pojo.Produit;
import fr.studi.stock.service.ProduitService;
import fr.studi.stock.ws.ProduitWs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProduitWs.class)
class StockApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProduitService produitService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetProduits() throws Exception {
		mockMvc.perform(get("/api/produit"))
				.andExpect(status().isOk());
	}


	@Test
	public void createProduit() throws Exception {
		Produit monProduit = new Produit("stylo","c'est un beau stylo",
                1.2F,5);
		mockMvc.perform(post("/api/produit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(monProduit)))
				.andExpect(status().isOk());
	}
}
