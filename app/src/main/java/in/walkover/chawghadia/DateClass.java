package in.walkover.chawghadia;

import java.util.Date;


public class DateClass {

    public DateClass() {

    }

    Date getNextDate(Date d) {
        if (d.getMonth() >= 0 && d.getMonth() <= 6) {
            if (d.getMonth() % 2 == 0)   //month with 31 days
            {
                if (d.getDate() == 31) {
                    d.setDate(1);    //if date  is 31
                    d.setMonth(d.getMonth() + 1);    //incrementing month

                } else {
                    d.setDate(d.getDate() + 1);
                    return d;
                }
            } else if (d.getMonth() % 2 != 0)     //month with 30 days
            {
                //------------------incrementing date of feb--------------------------
                if (d.getMonth() == 1) {
                    if (d.getDate() == 29)        //incrementing last date of feb of a leap year
                    {

                        d.setDate(1);
                        d.setMonth(2);
                        return d;
                    } else if (d.getDate() == 28)   //incrementing last date of feb of non leap year
                    {
                        if (d.getYear() % 4 == 0)  //incrementing  date of feb to 29 if its a leap year
                        {

                            d.setDate(29);
                            return d;
                        } else if (d.getYear() % 4 != 0)                            //incrementing  date of feb to 1 march if its a non leap year
                        {
                            d.setDate(1);
                            d.setMonth(2);
                            return d;
                        }
                    } else {
                        d.setDate(d.getDate() + 1);
                        return d;
                    }
                }
                //------------------incrementing date of feb done--------------------------
                if (d.getDate() == 30 && d.getMonth() > 2) {

                    d.setDate(1);
                    d.setMonth(d.getMonth() + 1);
                    return d;
                } else if (d.getDate() != 30 && d.getMonth() > 2) {
                    d.setDate(d.getDate() + 1);
                    return d;
                }
            }

        } else if (d.getMonth() > 6 && d.getMonth() <= 11) {
            if (d.getMonth() % 2 == 0)     //month with 30 days
            {
                if (d.getDate() == 30) {
                    d.setDate(1);    //if date  is 30
                    d.setMonth(d.getMonth() + 1);     //incrementing month
                    return d;
                } else {

                    d.setDate(d.getDate() + 1);
                    return d;
                }
            } else if (d.getMonth() % 2 != 0)    //month with 31 days
            {
                if (d.getDate() == 31) {

                    d.setDate(1);    //if date  is 31
                    if (d.getMonth() == 11) {
                        d.setMonth(0); //incrementing month to first month
                        d.setYear(d.getYear() + 1); //incrementing year
                        return d;
                    } else {
                        d.setMonth(d.getMonth() + 1);  //incrementing month
                        return d;
                    }

                } else {
                    d.setDate(d.getDate() + 1);
                    return d;
                }
            }
        }
        return d;
    }

    Date previousDate(Date d) {

        if (d.getMonth() >= 0 && d.getMonth() <= 6) {
            if (d.getDate() == 1) {

                if (d.getMonth() % 2 != 0) {
                    d.setMonth((d.getMonth() - 1));
                    d.setDate(31);
                    return d;
                }
                if (d.getMonth() % 2 == 0) {
                    if (d.getMonth() == 4 || d.getMonth() == 6) {
                        d.setDate(30);
                        d.setMonth((d.getMonth() - 1));
                        return d;
                    }
                    if (d.getMonth() == 0) {
                        d.setDate(31);
                        d.setMonth(11);
                        d.setYear(d.getYear() - 1);
                        return d;
                    }
                    if (d.getMonth() == 2) {
                        if (d.getYear() % 4 == 0) {
                            d.setDate(29);
                            d.setMonth(1);
                            return d;
                        } else if (d.getYear() % 4 != 0) {
                            d.setDate(28);
                            d.setMonth(1);
                            return d;
                        }
                    }
                }
            }
            if (d.getDate() != 1) {
                d.setDate((d.getDate() - 1));
                return d;
            }
        }
        if (d.getMonth() > 6 && d.getMonth() <= 11) {

            if (d.getDate() == 1 && d.getMonth() % 2 == 0) {
                d.setMonth(d.getMonth() - 1);
                d.setDate(31);
                return d;
            }
            if (d.getDate() == 1 && d.getMonth() % 2 != 0) {
                if (d.getMonth() == 7) {
                    d.setDate(31);
                    d.setMonth(6);
                    return d;
                }
                if (d.getMonth() == 9 || d.getMonth() == 11) {
                    d.setDate(30);
                    d.setMonth(d.getMonth() - 1);
                    return d;
                }
            }

            if (d.getDate() != 1) {
                d.setDate((d.getDate() - 1));
                return d;
            }
        }
        return d;
    }
}
