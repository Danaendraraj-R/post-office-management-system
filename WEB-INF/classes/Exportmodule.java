package org.Exportmodules;

import java.util.*;
import java.io.PrintWriter;

import java.io.*;
import java.sql.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public abstract class Exportmodule
{
public abstract ByteArrayOutputStream CreateFile(ResultSet rs);
}


