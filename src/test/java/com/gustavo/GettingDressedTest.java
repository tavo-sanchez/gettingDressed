package com.gustavo;

import org.junit.Assert;
import org.junit.Test;

public class GettingDressedTest {

    @Test
    public final void whenTwoStringArgumentsArePassedThenTrue(){
        GettingDressed.dress("HOT", "8,1,3,2,4");
        Assert.assertTrue(true);
    }

    // Test will fail when implemented only valid commands
    //@Test (expected = NumberFormatException.class)
    @Test
    public final void whenNonNumberIsUsedAtSecondArgumentThenExceptionIsThrown(){
        //GettingDressed.dress("HOT", "8,2,A");
        Assert.assertEquals("Removing PJs, shirt, fail", GettingDressed.dress("HOT", "8, 4, A"));
    }

    @Test
    public final void whenFirstArgumentPassedIsColdThenTrue(){
        GettingDressed.dress("COLD", "8,1,3,2,4");
        Assert.assertTrue(true);
    }

    @Test
    public final void whenCommandStringIsEmptyThenPrintTempetureAndFail(){
        Assert.assertEquals("fail", GettingDressed.dress("HOT", ""));
    }

    @Test
    public final void whenTempetureIsLowerCaseThenTrue(){
        GettingDressed.dress("hot", "8,1,2,3,4");
        Assert.assertTrue(true);
    }

    @Test
    public final void whenInicializateCommandsThenTrue(){
        GettingDressed.initCommands();
        Assert.assertTrue(true);
    }

    @Test
    public final void whenFirstCommandTakeOfPJThenTrue(){
        Assert.assertEquals("Removing PJs, fail", GettingDressed.dress("HOT", "8"));
    }

    @Test
    public final void whenFirstCommandColdTakeOfPJThenFail(){
        Assert.assertEquals("Removing PJs, fail", GettingDressed.dress("COLD", "8, 7"));
    }

    @Test
    public final void whenFirstCommandDifferentThan8ThenFailMessage(){
        Assert.assertEquals("fail", GettingDressed.dress("HOT", "6"));
    }

    // It will fails when rule no Socks when hot
    @Test
    public final void whenPassTwoValidArgumentsThenActionMessage(){
        //Assert.assertEquals("Removing PJs, shorts, shirt, sunglasses, sandals, leaving house", GettingDressed.dress("HOT", "8, 6, 4, 2, 3, 1, 7"));
        Assert.assertEquals("Removing PJs, shorts, shirt, sunglasses, fail",
                GettingDressed.dress("HOT", "8, 6, 4, 2, 3, 1, 7"));
    }

    //This test will fail when only 1 piece of each clothes rule were applied
    @Test
    public final void whenPassTwoValidArgumentsNoRulesThenActionMessage(){
        //Assert.assertEquals("Removing PJs, pants, pants, sunglasses", GettingDressed.dress("COLD", "8, 6, 6, 2"));
        Assert.assertEquals("Removing PJs, shorts, fail", GettingDressed.dress("HOT", "8, 6, 6"));
    }

    @Test
    public final void whenNoValidCommandThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, pants, fail",
                GettingDressed.dress("COLD", "8, 6, 12, 3"));
    }

    // Will fail when Shirt before headwear applies
    @Test
    public final void whenSameCommandTwiceThenSkipItAndPrint(){
        Assert.assertEquals("Removing PJs, shirt, sunglasses, fail",
                GettingDressed.dress("HOT", "8, 4, 2, 2"));
    }

    @Test
    public final void whenIsHotNoSocksThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, shirt, sunglasses, fail",
                GettingDressed.dress("HOT", "8, 4, 2, 3"));
    }

    // It will fail when Shirt before headwear
    @Test
    public final void whenIsHotNoJacketThenPrintFailAtEnd(){
        //Assert.assertEquals("Removing PJs, sunglasses, fail", GettingDressed.dress("HOT", "8, 2, 5, 4"));
        Assert.assertEquals("Removing PJs, shirt, sunglasses, fail",
                GettingDressed.dress("HOT", "8, 4, 2, 5"));
    }

    @Test
    public final void whenFootwearBeforeSocksThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, shirt, hat, fail",
                GettingDressed.dress("COLD", "8, 4, 2, 1, 3, 5"));
    }

    @Test
    public final void whenFootwearBeforePantsThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, shirt, hat, socks, fail",
                GettingDressed.dress("COLD", "8, 4, 2, 3, 1, 6, 5"));
    }

    @Test
    public final void whenShirtBeforeHeadwearThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, fail", GettingDressed.dress("COLD", "8, 2, 4, 3, 1, 6, 5"));
    }

    @Test
    public final void whenShirtBeforeJacketThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, pants, socks, fail",
                GettingDressed.dress("COLD", "8, 6, 3, 5, 4, 2, 1"));
    }

    @Test
    public final void whenAllCommandsWereUsedInHotThenTrue(){
        String result = GettingDressed.dress("HOT", "8, 6, 4, 2, 1, 7");
        result = result.replace(" ", "");
        String[] splitResult = result.split(",");
        Assert.assertEquals(6, splitResult.length);
    }

    @Test
    public final void whenNotAllCommandsWereUsedInHotThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, shorts, shirt, sunglasses, sandals, fail",
                GettingDressed.dress("HOT", "8, 6, 4, 2, 1"));

    }

    @Test
    public final void whenNotSevenAtEndThenPrintFailAtEnd(){
        Assert.assertEquals("Removing PJs, shorts, shirt, sunglasses, sandals, fail",
                GettingDressed.dress("HOT", "8, 6, 4, 2, 1"));
    }

    @Test
    public final void whenAllCommandsWereUsedInColdThenTrue(){
        String result = GettingDressed.dress("COLD", "8, 6, 4, 3, 5, 2, 1, 7");
        result = result.replace(" ", "");
        String[] splitResult = result.split(",");
        Assert.assertEquals(8, splitResult.length);
    }

    @Test
    public final void whenHotAllCorrectThenCorrectOutput(){
        Assert.assertEquals("Removing PJs, shorts, shirt, sunglasses, sandals, leaving house",
                GettingDressed.dress("HOT", "8, 6, 4, 2, 1, 7"));
    }

}