package riva

import com.appmattus.kotlinfixture.kotlinFixture
import model.Image
import model.ProductCollection
import org.jsoup.Jsoup
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.function.Executable

internal class RivaProductParserTest {

    @Test
    fun parse() {

        // arrange

        val html =
            """
                <html>
                <head/>
                <body>
                                                        <div class="cards__window-div type_23">
                    <span class="cards__window-subt">стелажи</span>
<div id="bx_3966226736_507_e7e5a53fd4b76e4dce4f311b34c340d4" data-entity="item" class="card__this">
		
<div class="card__img-wrap" data-id="507">
	<div class="card__img" id="bx_3966226736_507_e7e5a53fd4b76e4dce4f311b34c340d4_pict" style="background-image: url(/upload/iblock/8eb/%D0%92.%D0%90-1.jpg);"></div>
	<span id="bx_3966226736_507_e7e5a53fd4b76e4dce4f311b34c340d4_pict_slider" data-slider-interval="5000" data-slider-wrap="true" style="display: none;"></span>
</div>
    <div class="card__info product">
        <h4 class="product__name">Каркас антресоли узкой</h4>
        <p class="product__desc chair-card__article">Артикул:<span> В.А-1</span></p>
        <p class="product__desc">размеры: <span>388х360х369 мм.</span></p>
                  <p class="product__desc">цвет: <span class="val_tp"><span class="img_lab" style="background-image:url(/upload/uf/d4b/Венге-Цаво.jpg)"></span>Венге Цаво                        </span>
              <a href="javascript:void(0);" class="product__change">
                  <svg xmlns="http://www.w3.org/2000/svg" width="30" height="23.94" viewBox="0 0 30 23.94">

                      <path fill="curentColor" d="M1255.63,1728.78l3.87,2.4-12.5.83,0.89-10.41,3.87,3.74c1.56-1.32,1.72-2.4,1.93-4.53,0.52-5.17-6.04-7.95-8.71-6.93a27.187,27.187,0,0,1-1.93-2.87c3.67-2.04,16.55-1.9,16.45,10.51A8.323,8.323,0,0,1,1255.63,1728.78Zm-11.61,1.33a28.258,28.258,0,0,1,1.93,2.87c-3.67,2.06-16.55,1.91-16.45-10.53a8.375,8.375,0,0,1,3.87-7.29l-3.87-2.4,12.5-.83-0.89,10.43-3.87-3.74c-1.56,1.33-1.72,2.4-1.93,4.54C1234.79,1728.34,1241.35,1731.14,1244.02,1730.11Z" transform="translate(-1229.5 -1710.03)"></path>
                  </svg>
              </a>
          </p>
                <div class="product__quantity">
            <span class="product__stroke">количество</span>
            <a href="javascript:void(0);" class="product__ride product-item-amount-field-btn-disabled" id="bx_3966226736_507_e7e5a53fd4b76e4dce4f311b34c340d4_quant_down"></a>
<!--            <span class="product__item">5</span>-->
            <input class="product-item" id="bx_3966226736_507_e7e5a53fd4b76e4dce4f311b34c340d4_quantity" type="text" name="quantity" value="1">
            <a href="javascript:void(0);" class="product__ride product__ride--min" id="bx_3966226736_507_e7e5a53fd4b76e4dce4f311b34c340d4_quant_up"></a>
        </div>
	<div class="button__card not_user" onclick="${'$'}('a.signIn').click();">
            <span class="fill orange">
                  <button style="visibility:visible !important;" type="text" class="button">
                    <span class="butt open__map">выбрать</span>
                  </button>
                 <span class="cover"></span></span>
        </div>
    </div>
    </div>
    </body>
    </html>
        """

        val parser = RivaProductParser()
        val fixture = kotlinFixture()
        val productCollection = fixture<ProductCollection>()

        // act

        val product = parser.parse(Jsoup.parse(html), productCollection).last()

        // assert

        assertAll(
            Executable { assertEquals("Каркас антресоли узкой", product.name) },
            Executable { assertEquals("В.А-1", product.model) },
            Executable { assertEquals(36.9, product.height) },
            Executable { assertEquals(38.8, product.width) },
            Executable { assertEquals(36.0, product.length) },
            Executable { assertEquals(Image("https://riva.ru/upload/iblock/8eb/%D0%92.%D0%90-1.jpg", "v_a-1"), product.image) },
            Executable { assertEquals("v_a-1.jpg", product.image.fileName) },
            Executable { assertEquals("Стелажи", product.subcategory) },
        )
    }
}