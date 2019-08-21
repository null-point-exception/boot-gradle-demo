<template><div>
  <el-table :data="tableData" empty-text="当前数据没有，请添加数据" style="width: 100%">
    <el-table-column label="序号" align="center" type="index" min-width="20%"></el-table-column>
    <el-table-column label="用户名" align="center" min-width="20%" prop="name">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>用户名: {{ scope.row.name }}</p>
          <p>更新者: {{ fillEmpty(scope.row.updateBy) }}</p>
          <p>更新时间: {{ scope.row.updateDate }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.name }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="创建者" align="center" min-width="20%" :formatter="fillEmpty" prop="createBy">
    </el-table-column>
    <el-table-column label="创建日期" align="center" min-width="20%" prop="createDate">
      <template slot-scope="scope">
        <i class="el-icon-time"></i>
        <span style="margin-left: 10px">{{scope.row.createDate}}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center" min-width="20%">
      <template slot-scope="scope">
        <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
    <div slot="empty">
        <el-button size="small" type="primary"> 添加 </el-button>
    </div>
  </el-table>
</div>

</template>

<script>
export default {
  name: 'Users',
  data () {
    return {
      msg: 'User List',
      tableData: [],
    }
  },
  methods: {
    getUsers () {
      let self = this
      // 请求后台
      this.$axios({
        method: 'post',
        url: '/api/user/query',
        data: {},
      }).then((result) => {
        if (!result) return;
        self.tableData = result.data;
      })
    },
    handleEdit(index, row) {
      alert("编辑成功")
      console.log(index, row);
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log(index, row);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      });
    },
    fillEmpty(row, column, cellValue, index){
      return cellValue || "--";
    }
  },
  mounted() {
    this.getUsers();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
