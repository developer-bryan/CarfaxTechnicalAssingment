package com.bryanmills.carfaxtechnicalassigment

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.bryanmills.core.models.Dealer
import com.bryanmills.core.models.FirstPhoto
import com.bryanmills.core.models.Images
import com.bryanmills.core.models.Listings
import com.bryanmills.core.utils.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

const val HEADER_URL = "https://<some-host>/<photo-path>"

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class ExtensionsTest {

    lateinit var context: Context

    lateinit var sut: Listings
    lateinit var dealer: Dealer
    lateinit var images: Images

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        dealer = Dealer("San Diego", "1234567890", "CA")
        images = Images(FirstPhoto(HEADER_URL))

        sut = Listings(
            "Coupe",
            43499.0,
            dealer,
            "392",
            "RWD",
            "6.4 L",
            "Gasoline",
            "Brown",
            "Brown",
            images,
            "Dodge",
            1978,
            "Challenger",
            "Manual",
            "R/T Scat Pack",
            2021
        )
    }

    @Test
    fun `create location, with non empty city and state returns properly appended location`() {
        val expected = "San Diego, CA"
        val actual = sut.createLocation(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `create location, with empty city and not empty state returns state`() {
        val localSut = Listings(dealer = Dealer(null, null, "CA"))

        val expected = "CA"
        val actual = localSut.createLocation(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `create location, with not empty city and empty state returns city`() {
        val localSut = Listings(dealer = Dealer("San Diego", null, null))

        val expected = "San Diego"
        val actual = localSut.createLocation(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `create location, with empty city and state returns city`() {
        val localSut = Listings(dealer = Dealer(null, null, null))

        val expected = ""
        val actual = localSut.createLocation(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `create title returns properly appended title`() {
        val expected = "2021 Dodge Challenger R/T Scat Pack"
        val actual = sut.createTitle()
        assertEquals(expected, actual)
    }

    @Test
    fun `returns proper header photo`() {
        val expected = HEADER_URL
        val actual = sut.headerPhoto()
        assertEquals(expected, actual)
    }

    @Test
    fun `price formatted properly`() {
        val expected = "$ 43,499"
        val actual = sut.formattedPrice(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `exception handled with improper data to format for price`() {
        val localSut = Listings(currentPrice = null)
        val expected = ""
        val actual = localSut.formattedPrice(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `mileage formatted properly`() {
        val expected = "1,978k mi"
        val actual = sut.formattedMileage(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `exception handled with improper data to format for mileage`() {
        val localSut = Listings(mileage = null)
        val expected = ""
        val actual = localSut.formattedMileage(context)
        assertEquals(expected, actual)
    }

    @Test
    fun `proper phone number is returned`() {
        val expected = "1234567890"
        val actual = sut.phoneNumber()
        assertEquals(expected, actual)
    }

    @Test
    fun `engine and displacement appended properly`() {
        val expected = "6.4 L 392"
        val actual = sut.createEngineAndDisplacement()
        assertEquals(expected, actual)
    }

}