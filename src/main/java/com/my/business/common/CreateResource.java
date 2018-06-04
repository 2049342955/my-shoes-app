package com.my.business.common;

import com.my.business.entity.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateResource {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String args[]) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
//        createdFile(createController("org", CiipOrgCompanies.class, "Company"),
//                "D:\\beans\\" + sdf.format(new Date()) + "\\org\\controller\\" + "Company" + "Controller.java");
        createdFile(
                "D:\\beans\\" + sdf.format(new Date()) + "\\meu\\",
                "meu",
                new String[]{"ItemCategory"},
                User.class);

    }


    public static String createController(String modelName, Class clazz, String resourceName) {
        String simpleName = clazz.getSimpleName();
        //String packageModelName = ("usr".equals(modelName) ? "user" : modelName);
        //package
        StringBuffer result = new StringBuffer("com.my.business.web;\n\n");
        //import
        result.append(
                "import com.esudian.domain." + modelName + "." + simpleName + ";\n" +
                "import com.github.pagehelper.PageInfo;\n" +
                "import com.pudding.core.web.ResponseEntity;\n" +
                "import com.pudding.core.web.BaseController;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "import java.util.Date;\n" +
                "import java.util.List;\n");
        //Class def
        result.append("\n");
        result.append("\n@RestController");
        result.append("\n@RequestMapping(\"/" + toLowerFirst(resourceName) + "\")");
        result.append("\npublic class " + resourceName + "Controller extends BaseController {");
        result.append("\n    @Autowired");
        result.append("\n    private " + resourceName + "ResourceClient " + toLowerFirst(resourceName) + "ResourceClient;\n");

        //get
        result.append("\n    @RequestMapping(\"/get\")");
        result.append("\n    public ResponseEntity<" + simpleName + "> get(String id) {");
        result.append("\n        return " + toLowerFirst(resourceName) + "ResourceClient.get(id);");
        result.append("\n    }\n");
        //query
        result.append("\n    @RequestMapping(\"/query\")");
        result.append("\n    public ResponseEntity<PageInfo<" + simpleName + ">> query(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        return " + toLowerFirst(resourceName) + "ResourceClient.query(" + toLowerFirst(simpleName) + ");");
        result.append("\n    }\n");
        //list
        result.append("\n    @RequestMapping(\"/list\")");
        result.append("\n    public ResponseEntity<List<" + simpleName + ">> list(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        return " + toLowerFirst(resourceName) + "ResourceClient.list(" + toLowerFirst(simpleName) + ");");
        result.append("\n    }\n");
        //save
        result.append("\n    @RequestMapping(value = \"/save\", method = RequestMethod.POST)");
        result.append("\n    public ResponseEntity<" + simpleName + "> save(@RequestBody " + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        return " + toLowerFirst(resourceName) + "ResourceClient.save(" + toLowerFirst(simpleName) + ");");
        result.append("\n    }\n");

        //收尾
        result.append("}");
        return result.toString();
    }

    public static String createResource(String modelName, Class clazz, String resourceName) {
        String simpleName = clazz.getSimpleName();
        StringBuffer result = new StringBuffer("package com.esudian." + modelName + ".web;\n\n");
        result.append("import com.esudian.domain." + modelName + "." + simpleName + ";\n" +
                "import com.esudian." + modelName + ".service." + simpleName + "Service;\n" +
                "import com.github.pagehelper.PageInfo;\n" +
                "import com.pudding.core.web.BaseController;\n" +
                "import com.pudding.core.web.ResponseEntity;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n\n" +
                "import java.util.List;\n");
        result.append("\n@RestController");
        result.append("\n@RequestMapping(\"/" + toLowerFirst(resourceName) + "\")");
        result.append("\n");
        result.append("public class " + resourceName + "Resource extends BaseController {");
        result.append("\n    @Autowired");
        result.append("\n    ");
        result.append("private " + simpleName + "Service " + toLowerFirst(simpleName) + "Service;\n");
        //get
        result.append("\n    @RequestMapping(value = \"/get\", method = RequestMethod.GET)");
        result.append("\n    ");
        result.append("public ResponseEntity<" + simpleName + "> get" + "(String id) {");
        result.append("\n        ");
        result.append("return success(" + toLowerFirst(simpleName) + "Service.get" + "(id)" + ");");
        result.append("\n    }\n");

        //selectOne
        result.append("\n    @RequestMapping(value = \"/selectOne\", method = RequestMethod.GET)");
        result.append("\n    ");
        result.append("public ResponseEntity<" + simpleName + "> selectOne" + "(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("return success(" + toLowerFirst(simpleName) + "Service.selectOne" + "(" + toLowerFirst(simpleName) + "));");
        result.append("\n    }\n");

        //query
        result.append("\n    ");
        result.append("@RequestMapping(value = \"/query\", method = RequestMethod.GET)");
        result.append("\n    ");
        result.append("public ResponseEntity<PageInfo<" + simpleName + ">> query(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("return success(" + toLowerFirst(simpleName) + "Service.find" + "(" + toLowerFirst(simpleName) + ")" + ");");
        result.append("\n    }\n");

        //list
        result.append("\n    ");
        result.append("@RequestMapping(value = \"/list\", method = RequestMethod.GET)");
        result.append("\n    ");
        result.append("public ResponseEntity<List<" + simpleName + ">> list" + "(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("return success(" + toLowerFirst(simpleName) + "Service.findList" + "(" + toLowerFirst(simpleName) + ")" + ");");
        result.append("\n    }\n");

        //save
        result.append("\n    ");
        result.append("@RequestMapping(value = \"/save\", method = RequestMethod.POST)");
        result.append("\n    ");
        result.append("public ResponseEntity<" + simpleName + "> save" + "(@RequestBody " + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("return success(" + toLowerFirst(simpleName) + "Service.save" + "(" + toLowerFirst(simpleName) + ")" + ");");
        result.append("\n    }");
        result.append("\n}");
        return result.toString();
    }


    /**
     * @param clazz
     * @return
     */
    public static String createServiceInterface(String modelName, Class clazz) {
        String simpleName = clazz.getSimpleName();
        StringBuffer result = new StringBuffer("package com.esudian." + modelName + ".service;\n");
        result.append("\nimport " + clazz.getName() + ";");
        result.append("\nimport com.github.pagehelper.PageInfo;\n");
        result.append("\nimport java.util.List;\n");
        result.append("\n/**" +
                "\n * date " + simpleDateFormat.format(new Date()) +
                "\n * description " + simpleName + "service" +
                "\n */");
        result.append("\n");
        result.append("public interface " + simpleName + "Service {");

        //查询单个
        result.append("\n");
        result.append("    /**" +
                "\n     * 根据条件查询一个" +
                "\n     *" +
                "\n     * @param " + toLowerFirst(simpleName) +
                "\n     * @return " + simpleName +
                "\n     */");
        result.append("\n");
        result.append("    public " + simpleName + " selectOne");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ");\n");


        //根据查询单个
        result.append("\n");
        result.append("    /**" +
                "\n     * 根据id查询" +
                "\n     *" +
                "\n     * @param id" +
                "\n     * @return " + simpleName +
                "\n     */");
        result.append("\n");
        result.append("    public " + simpleName + " get");
        result.append("(String id);\n\n");

        //查询list 不分页 result.append("\n");result.append("\n");
        result.append("    /**" +
                "\n     * 查询符合条件的数据,不分页" +
                "\n     *" +
                "\n     * @param " + toLowerFirst(simpleName) +
                "\n     * @return " + "List<" + simpleName + ">" +
                "\n     */");
        result.append("\n");
        result.append("    public List<" + simpleName + "> findList");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ");\n");


        //查询所有 list
        result.append("\n");
        result.append("    /**" +
                "\n     * 查询所有数据" +
                "\n     *" +
                "\n     * @return " + "List<" + simpleName + ">" +
                "\n     */");
        result.append("\n");
        result.append("    public List<" + simpleName + "> findAll" + "();\n");

        //查询符合条件的数据,并且分页,默认pageNum=1,pageSize=100
        result.append("\n");
        result.append("    /**" +
                "\n     * 查询符合条件的数据,并且分页,默认pageNum=1,pageSize=100" +
                "\n     *" +
                "\n     * @param " + toLowerFirst(simpleName) +
                "\n     * @return " + "PageInfo<" + simpleName + ">" +
                "\n     */");
        result.append("\n");
        result.append("    public PageInfo<" + simpleName + "> find");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ");\n");

        //保存
        result.append("\n");
        result.append("    /**" +
                "\n     * 保存" +
                "\n     *" +
                "\n     * @param " + toLowerFirst(simpleName) +
                "\n     * @return " + simpleName +
                "\n     */");
        result.append("\n");
        result.append("    public " + simpleName + " save");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ");\n");

        //删除符合条件的数据
        result.append("\n");
        result.append("    /**" +
                "\n     * 删除符合条件的数据" +
                "\n     *" +
                "\n     * @param " + toLowerFirst(simpleName) +
                "\n     * @return int 删除条数" +
                "\n     */");
        result.append("\n");
        result.append("    public int delete");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ");");
        result.append("\n");
        result.append("}");
        return result.toString();
    }

    public static String createServiceInterfaceImpl(String modelName, Class clazz) {
        String simpleName = clazz.getSimpleName();
        StringBuffer result = new StringBuffer("package com.esudian." + modelName + ".service.impl;\n\n");
        String mapperProName = toLowerFirst(simpleName) + "Mapper";

        result.append("import com.esudian.domain.BaseDomain;\n" +
                "import com.esudian.domain." + modelName + "." + simpleName + ";\n" +
                "import com.esudian." + modelName + ".mappers." + simpleName + "Mapper;\n" +
                "import com.esudian." + modelName + ".service." + simpleName + "Service;\n" +
                "import com.github.pagehelper.ISelect;\n" +
                "import com.github.pagehelper.PageHelper;\n" +
                "import com.github.pagehelper.PageInfo;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import org.springframework.util.StringUtils;\n" +
                "\n" +
                "import java.util.List;\n");

        result.append("\n/**" +
                "\n * date " + simpleDateFormat.format(new Date()) +
                "\n * description " + simpleName + "service" +
                "\n */");
        result.append("\n@Service");
        result.append("\n");
        result.append("public class " + simpleName + "ServiceImpl implements " + simpleName + "Service {");

        //Mapper
        result.append("\n    @Autowired");
        result.append("\n    private " + simpleName + "Mapper " + toLowerFirst(simpleName) + "Mapper;\n");


        //查询list 不分页 result.append("\n");result.append("\n");
        result.append("\n    @Override");
        result.append("\n    public List<" + simpleName + "> findList");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("return " + mapperProName + ".select(" + toLowerFirst(simpleName) + ");");
        result.append("\n    }\n");

        //查询单个
        result.append("\n");
        result.append("    @Override");
        result.append("\n");
        result.append("    public " + simpleName + " selectOne");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("return " + mapperProName + ".selectOne(" + toLowerFirst(simpleName) + ");");
        result.append("\n    }\n");


        //查询单个
        result.append("\n");
        result.append("    @Override");
        result.append("\n");
        result.append("    public " + simpleName + " get");
        result.append("(String id) {");
        result.append("\n        ");
        result.append(simpleName + " " + toLowerFirst(simpleName) + " = new " + simpleName + "();");
        result.append("\n        " + toLowerFirst(simpleName) + ".setId(id);");
        result.append("\n        ");
        result.append("return " + mapperProName + ".selectByPrimaryKey(" + toLowerFirst(simpleName) + ");");
        result.append("\n    }\n");


        //查询所有
        result.append("\n");
        result.append("    @Override");
        result.append("\n");
        result.append("    public List<" + simpleName + "> findAll");
        result.append("() {");
        result.append("\n        ");
        result.append("return " + mapperProName + ".selectAll();");
        result.append("\n    }\n");


        //查询符合条件的数据 分页
        result.append("\n");
        result.append("    @Override");
        result.append("\n");
        result.append("    public PageInfo<" + simpleName + "> find");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("if (" + toLowerFirst(simpleName) + ".getPageNum() == null)");
        result.append("\n            ");
        result.append(toLowerFirst(simpleName) + ".setPageNum(BaseDomain.DEFALUT_PAGE_NUM);");
        result.append("\n        ");
        result.append("if (" + toLowerFirst(simpleName) + ".getPageSize() == null)");
        result.append("\n            ");
        result.append(toLowerFirst(simpleName) + ".setPageSize(BaseDomain.DEFALUT_PAGE_SIZE);");
        result.append("\n        ");
        result.append("return PageHelper.startPage(" + toLowerFirst(simpleName) + ".getPageNum(), " + toLowerFirst(simpleName) + ".getPageSize())." +
                "setOrderBy(" + toLowerFirst(simpleName) + ".getOrderBy()).doSelectPageInfo(new ISelect() {\n" +
                "            @Override\n" +
                "            public void doSelect() {\n" +
                "                " + mapperProName + ".select(" + toLowerFirst(simpleName) + ");\n " +
                "           }\n" +
                "        });");
        result.append("\n    }\n");


        //保存
        result.append("\n");
        result.append("    @Override");
        result.append("\n");
        result.append("    public " + simpleName + " save");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("if (StringUtils.isEmpty(" + toLowerFirst(simpleName) + ".getId())) {\n" +
                "            " + mapperProName + ".insert(" + toLowerFirst(simpleName) + ");\n" +
                "        } else {\n" +
                "            " + mapperProName + ".updateByPrimaryKeySelective(" + toLowerFirst(simpleName) + ");\n" +
                "        }");
        result.append("\n        return " + toLowerFirst(simpleName) + ";");
        result.append("\n    }\n");


        //删除符合条件的数据
        result.append("\n");
        result.append("    @Override");
        result.append("\n");
        result.append("    public int delete");
        result.append("(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        ");
        result.append("return " + mapperProName + ".delete(" + toLowerFirst(simpleName) + ");");
        result.append("\n    }");
        result.append("\n}");

        return result.toString();
    }

    public static String createMapper(String modelName, Class clazz) {
        String simpleName = clazz.getSimpleName();
        StringBuffer result = new StringBuffer("package com.esudian." + modelName + ".mapper;");
        result.append("\nimport " + clazz.getName() + ";");
        result.append("\nimport tk.mybatis.mapper.common.Mapper;");
        result.append("\npublic interface " + simpleName + "Mapper extends Mapper<" + simpleName + ">{");
        result.append("\n}");
        return result.toString();
    }

    private static String toLowerFirst(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    public static String createResourceClient(String modelName, Class clazz, String resourceName) {
        String simpleName = clazz.getSimpleName();
        //package
        StringBuffer result = new StringBuffer("package com.esudian.client" + modelName + ";\n\n");
        //import
        result.append("import com.esudian.common.ServiceNameConstant;\n"
                + "import com.esudian.domain." + modelName + "." + simpleName + ";\n"
                + "import com.github.pagehelper.PageInfo;\n"
                + "import com.pudding.core.client.AbstractResourceClient;\n"
                + "import com.pudding.core.web.ResponseEntity;\n"
                + "import org.springframework.stereotype.Component;\n\n");

        //import interface
        result.append("import java.util.HashMap;\n"
                + "import java.util.List;\n"
                + "import java.util.Map;\n\n");

        //ClassDe
        result.append("@Component\n");
        result.append("public class " + resourceName + "ResourceClient extends AbstractResourceClient<ResponseEntity> {\n");

        //重写getServiceName
        String serviceName = "";
        if ("usr".equals(modelName)) {
            serviceName = "USER_SERVICE";
        }
        if ("meu".equals(modelName)) {
            serviceName = "MEU_SERVICE";
        }
        if ("org".equals(modelName)) {
            serviceName = "ORG_SERVICE";
        }
        if ("mdm".equals(modelName)) {
            serviceName = "MDM_SERVICE";
        }
        if ("message".equals(modelName)) {
            serviceName = "MESSAGE_SERVICE";
        }
        if ("om".equals(modelName)) {
            serviceName = "OM_SERVICE";
        }
        if ("board".equals(modelName)) {
            serviceName = "QUEUE_BOARD";
        }
        result.append("    @Override\n" +
                "    protected String getServiceName() {\n" +
                "        return ServiceNameConstant." + serviceName + ";\n" +
                "    }\n");

        //url
        result.append("\n    public static final String GET = \"/" + toLowerFirst(resourceName) + "/" + "get\";");
        result.append("\n    public static final String LIST = \"/" + toLowerFirst(resourceName) + "/" + "list\";");
        result.append("\n    public static final String QUERY = \"/" + toLowerFirst(resourceName) + "/" + "query\";");
        result.append("\n    public static final String SAVE = \"/" + toLowerFirst(resourceName) + "/" + "save\";");
        result.append("\n    public static final String SELECT_ONE = \"/" + toLowerFirst(resourceName) + "/" + "selectOne\";");

        //get
        result.append("\n");
        result.append("\n    public ResponseEntity<" + simpleName + "> get(String id) {");
        result.append("\n        Map<String, String> urlParams = new HashMap<>(1);");
        result.append("\n        urlParams.put(\"id\", id);");
        result.append("\n        return successBean(" +
                "\n                (Map<String, Object>) getForObject(GET, ResponseEntity.class, urlParams).getData()," +
                "\n                " + simpleName + ".class);");
        result.append("\n    }");

        //list
        result.append("\n");
        result.append("\n    public ResponseEntity<List<" + simpleName + ">> list(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        return successList(" +
                "\n                (List<Map<String, Object>>) getForObject(LIST, ResponseEntity.class, " + toLowerFirst(simpleName) + ").getData()," +
                "\n                " + simpleName + ".class);");
        result.append("\n    }");

        //query
        result.append("\n");
        result.append("\n    public ResponseEntity<PageInfo<" + simpleName + ">> query(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        return successPageInfo(" +
                "\n                (Map<String, Object>) getForObject(QUERY, ResponseEntity.class, " + toLowerFirst(simpleName) + ").getData()," +
                "\n                " + simpleName + ".class);");
        result.append("\n    }");

        //save
        result.append("\n");
        result.append("\n    public ResponseEntity<" + simpleName + "> save(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        return successBean(" +
                "\n                (Map<String, Object>) postForObject(SAVE, " + toLowerFirst(simpleName) + ", ResponseEntity.class).getData()," +
                "\n                " + simpleName + ".class);");
        result.append("\n    }");

        //selectOne
        result.append("\n");
        result.append("\n    public ResponseEntity<" + simpleName + "> selectOne(" + simpleName + " " + toLowerFirst(simpleName) + ") {");
        result.append("\n        return successBean(" +
                "\n                (Map<String, Object>) getForObject(SELECT_ONE,  ResponseEntity.class, " + toLowerFirst(simpleName) + ").getData()," +
                "\n                " + simpleName + ".class);");
        result.append("\n    }");
        //收尾
        result.append("\n}");
        return result.toString();
    }

    public static void createdFile(String text, String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            String file = fileName.substring(0, fileName.lastIndexOf("\\"));
            File fs = new File(file);
            if (!fs.exists()) {
                fs.mkdirs();
            }
            f.createNewFile();
        }
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(fileName);
            o.write(text.getBytes("UTF-8"));
            o.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void createdFile(String path, String modelName, Class clazz) throws IOException {
        createdFile(createServiceInterface(modelName, clazz), path + "service\\" + clazz.getSimpleName() + "Service.java");
        createdFile(createServiceInterfaceImpl(modelName, clazz), path + "service\\impl\\" + clazz.getSimpleName() + "ServiceImpl.java");
//        createdFile(createResource(modelName, clazz), path + "web\\" + clazz.getSimpleName() + "Resource.java");
//        createdFile(createServiceInterfaceImpl(modelName, clazz), path + "mapper\\" + clazz.getSimpleName() + "Mapper.java");
    }

    /**
     * @param path          生成文件路径
     * @param modelName     模块名
     * @param resourceNames 生成Resource.java的名字 和Class的数组一一对应
     * @param clazzs        要生成的class数组
     * @throws IOException
     */
    public static void createdFile(String path, String modelName, String[] resourceNames, Class... clazzs) throws IOException {
        for (int i = 0; i < clazzs.length; i++) {
            createdFile(createServiceInterface(modelName, clazzs[i]), path + "service\\" + clazzs[i].getSimpleName() + "Service.java");
            createdFile(createServiceInterfaceImpl(modelName, clazzs[i]), path + "service\\impl\\" + clazzs[i].getSimpleName() + "ServiceImpl.java");
            createdFile(createResource(modelName, clazzs[i], resourceNames[i]), path + "web\\" + resourceNames[i] + "Resource.java");
            createdFile(createResourceClient(modelName, clazzs[i], resourceNames[i]),
                    path + "\\client\\" + resourceNames[i] + "ResourceClient.java");
            createdFile(createController(modelName, clazzs[i], resourceNames[i]), path + "controller\\" + resourceNames[i] + "Controller.java");
        }

//        createdFile(createServiceInterfaceImpl(modelName, clazz), path + "mapper\\" + clazz.getSimpleName() + "Mapper.java");
    }

    public static void createdFile(String path, String modelName, Class... clazzs) throws IOException {
        for (Class clazz : clazzs) {
            createdFile(path, modelName, clazz);
        }
    }

}
