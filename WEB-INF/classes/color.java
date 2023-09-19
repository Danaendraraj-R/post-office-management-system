package org.Exportmodules;

public class color implements Conversion{
@Override
public String convert(Boolean A)
{
if(A)
return "White";
else
return "Black";
}
}